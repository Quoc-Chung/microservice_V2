package com.quocchung.notificationservice.consumer;

import com.quocchung.notificationservice.client.UserClient;
import com.quocchung.notificationservice.dto.OrderResponse.UserResponse;
import com.quocchung.notificationservice.event.OrderCreatedEvent;
import com.quocchung.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

  private final EmailService emailService;
  private final UserClient userClient;

  @KafkaListener(
      topics = "order-created",
      groupId = "notification-group",
      containerFactory = "kafkaListenerContainerFactory"
  )
  public void handleOrderCreated(OrderCreatedEvent event) {
    log.info("Nhận event order-created, orderId={}, userId={}",
        event.getOrderId(), event.getUserId());
    try {

      UserResponse user = userClient.getUserResponse(event.getUserId());
      emailService.sendOrderConfirmationEmail(user.getEmail(), event);
    } catch (Exception e) {
      log.error("Xử lý event thất bại, orderId={}: {}", event.getOrderId(), e.getMessage());
    }
  }
}
