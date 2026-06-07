package com.quocchung.notificationservice.service;

import com.quocchung.notificationservice.event.OrderCreatedEvent;

public interface EmailService {
  void sendOrderConfirmationEmail(String toEmail, OrderCreatedEvent event);
}
