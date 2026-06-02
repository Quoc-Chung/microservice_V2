package com.quocchung.orderservice.client.fallback;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import com.quocchung.orderservice.client.UserClient;
import com.quocchung.orderservice.model.dto.OrderResponse.UserResponse;
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
        log.error("Fallback getUserById: id={}, error={}", id, cause.getMessage());
        return new UserResponse();
      }
    };
  }
}
