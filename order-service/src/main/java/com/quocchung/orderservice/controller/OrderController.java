package com.quocchung.orderservice.controller;

import com.quocchung.orderservice.client.UserClient;
import com.quocchung.orderservice.model.dto.OrderResponse;
import com.quocchung.orderservice.model.dto.OrderResponse.UserResponse;
import com.quocchung.orderservice.model.entity.Order;
import com.quocchung.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderRepository orderRepository;
  private final UserClient userClient;

  @PostMapping
  public Order createOrder(@RequestBody Order order) {
    return orderRepository.save(order);
  }

  @GetMapping
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @GetMapping("/details/{order_id}")
  public OrderResponse getOrder(@PathVariable(name = "order_id") Long orderId){
     Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Khong tim thay đơn hàng"));

    UserResponse user = userClient.getUserResponse(order.getUserId());

     return OrderResponse
         .builder()
         .id(order.getId())
         .price(order.getPrice())
         .product(order.getProduct())
         .userResponse(user)
         .build();
  }
}