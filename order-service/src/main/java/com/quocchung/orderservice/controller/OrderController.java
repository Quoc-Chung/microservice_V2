package com.quocchung.orderservice.controller;
import com.quocchung.orderservice.grpc.GrpcUserClient;
import com.quocchung.orderservice.grpc.UserResponse;
import com.quocchung.orderservice.model.dto.CreateOrderRequest;
import com.quocchung.orderservice.model.dto.OrderCreateResponse;
import com.quocchung.orderservice.model.dto.UserDTO;
import com.quocchung.orderservice.model.entity.Order;
import com.quocchung.orderservice.repository.OrderRepository;
import com.quocchung.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderRepository orderRepository;
  private final OrderService orderService;
  private final GrpcUserClient grpcUserClient;

  @PostMapping
  public OrderCreateResponse createOrder(@RequestBody CreateOrderRequest order) {
    return orderService.createOrder(order) ;
  }

  @GetMapping
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @GetMapping("/grpc/{id}")
  public UserDTO getOrderGrpc(@PathVariable Long id) {
    Order order = orderRepository.findById(id).orElseThrow();
    UserResponse user = grpcUserClient.getUserById(order.getUserId());

    return UserDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build();
  }

}