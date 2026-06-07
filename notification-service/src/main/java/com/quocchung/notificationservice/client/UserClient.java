package com.quocchung.notificationservice.client;

import com.quocchung.notificationservice.client.fallback.UserClientFallbackFactory;
import com.quocchung.notificationservice.dto.OrderResponse.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "user-service",
    path = "/api/users",
    fallbackFactory = UserClientFallbackFactory.class
)
public interface UserClient {
  @GetMapping("/detail/{userId}")
  UserResponse getUserResponse(@PathVariable Long userId);
}
