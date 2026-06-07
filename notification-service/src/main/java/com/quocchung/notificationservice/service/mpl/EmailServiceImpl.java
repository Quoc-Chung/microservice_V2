package com.quocchung.notificationservice.service.mpl;

import com.quocchung.notificationservice.event.OrderCreatedEvent;
import com.quocchung.notificationservice.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
  private final JavaMailSender mailSender;
  private final TemplateEngine templateEngine;

  @Override
  public void sendOrderConfirmationEmail(String toEmail, OrderCreatedEvent event) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setFrom("noreply@yourshop.com");
      helper.setTo(toEmail);
      helper.setSubject("Đặt hàng thành công - Đơn hàng #" + event.getOrderId());


      Context context = new Context();
      context.setVariable("orderId", event.getOrderId());
      context.setVariable("totalAmount", event.getTotalAmount());
      context.setVariable("orderDate", event.getOrderDate());
      context.setVariable("items", event.getItems());

      String htmlContent = templateEngine.process("order-confirmation", context);
      helper.setText(htmlContent, true);

      mailSender.send(message);
      log.info("Đã gửi email xác nhận đơn hàng #{} tới {}", event.getOrderId(), toEmail);

    } catch (MessagingException e) {
      log.error("Lỗi gửi email cho đơn hàng #{}: {}", event.getOrderId(), e.getMessage());
    }
  }
}
