package com.quocchung.orderservice.model.entity;
import com.quocchung.orderservice.utils.OrderStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;

  private String product;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<OrderItem> items = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  private Double totalAmount;
  private LocalDateTime orderDate;
}