package com.quocchung.notificationservice.client.fallback;

import com.quocchung.notificationservice.client.UserClient;
import com.quocchung.notificationservice.dto.OrderResponse.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {

  @Override
  public UserClient create(Throwable cause) {
    return new UserClient() {
      @Override
      public UserResponse getUserResponse(Long userId) {
        log.error("Fallback getUserById: id={}, error={}", userId, cause.getMessage());
        return new UserResponse();
      }
    };
  }
}
