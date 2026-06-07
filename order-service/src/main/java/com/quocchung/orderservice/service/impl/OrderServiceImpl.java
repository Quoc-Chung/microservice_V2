package com.quocchung.orderservice.service.impl;

import com.quocchung.orderservice.client.ProductClient;
import com.quocchung.orderservice.client.UserClient;
import com.quocchung.orderservice.event.OrderCreatedEvent;
import com.quocchung.orderservice.model.dto.CreateOrderRequest;
import com.quocchung.orderservice.model.dto.OrderCreateResponse;
import com.quocchung.orderservice.model.dto.OrderCreateResponse.OrderCreateItem;
import com.quocchung.orderservice.model.dto.OrderCreateResponse.UserCreateResponse;
import com.quocchung.orderservice.model.dto.OrderResponse.UserResponse;
import com.quocchung.orderservice.model.dto.ProductResponse;
import com.quocchung.orderservice.model.entity.Order;
import com.quocchung.orderservice.model.entity.OrderItem;
import com.quocchung.orderservice.repository.OrderItemRepository;
import com.quocchung.orderservice.repository.OrderRepository;
import com.quocchung.orderservice.service.OrderService;
import com.quocchung.orderservice.utils.OrderStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
  private final UserClient userClient;
  private final ProductClient productClient;
  private final OrderItemRepository orderItemRepository;

  @Override
  public OrderCreateResponse createOrder(CreateOrderRequest order) {
    UserResponse userResponse = userClient.getUserResponse(order.getUserId());

    Order newOrder = Order.builder()
        .userId(userResponse.getId())
        .status(OrderStatus.PENDING)
        .orderDate(LocalDateTime.now())
        .build();

    UserCreateResponse userCreateResponse = UserCreateResponse.builder()
        .userId(userResponse.getId())
        .name(userResponse.getName())
        .email(userResponse.getEmail())
        .build();

    List<OrderItem> orderItems = new ArrayList<>();
    List<OrderCreateItem> orderCreateItemList = new ArrayList<>();
    AtomicReference<Double> totalAmount = new AtomicReference<>(0.0);

    order.getItems().forEach(itemRequest -> {
      ProductResponse productResponse = productClient.getProduct(itemRequest.getProductId());
      if (productResponse != null) {
        totalAmount.updateAndGet(v -> v + itemRequest.getQuantity() * productResponse.getPrice());

        orderItems.add(OrderItem.builder()
            .order(newOrder)
            .productId(productResponse.getProductId())
            .quantity(itemRequest.getQuantity())
            .build());

        orderCreateItemList.add(new OrderCreateItem(
            productResponse.getProductId(),
            productResponse.getCode(),
            productResponse.getName(),
            itemRequest.getQuantity()
        ));
      }
    });

    newOrder.setTotalAmount(totalAmount.get());
    orderRepository.save(newOrder);
    orderItemRepository.saveAll(orderItems);
    log.info("TẠO ĐƠN HÀNG THÀNH CÔNG");

    OrderCreatedEvent event = OrderCreatedEvent.builder()
        .orderId(newOrder.getId())
        .userId(newOrder.getUserId())
        .totalAmount(totalAmount.get())
        .orderDate(newOrder.getOrderDate())
        .items(orderItems.stream()
            .map(item -> OrderCreatedEvent.OrderItemEvent.builder()
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .build())
            .collect(Collectors.toList()))
        .build();
    kafkaTemplate.send("order-created", String.valueOf(newOrder.getId()), event);
    log.info("Đã gửi event Kafka: order-created, orderId={}", newOrder.getId());


    return OrderCreateResponse.builder()
        .userCreateResponse(userCreateResponse)
        .orderCreateItemList(orderCreateItemList)
        .createDate(LocalDateTime.now())
        .totalAmount(totalAmount.get())
        .build();
  }

}
