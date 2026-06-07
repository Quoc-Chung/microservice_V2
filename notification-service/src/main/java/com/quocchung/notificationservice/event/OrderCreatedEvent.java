package com.quocchung.notificationservice.event;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {
  private Long orderId;
  private Long userId;
  private Double totalAmount;
  private LocalDateTime orderDate;
  private List<OrderItemEvent> items;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class OrderItemEvent {
    private Long productId;
    private Integer quantity;
  }
}
