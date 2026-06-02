package com.quocchung.orderservice.client;

import com.quocchung.orderservice.client.fallback.UserClientFallbackFactory;
import com.quocchung.orderservice.model.dto.OrderResponse.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "user-service",
    path = "/api/users",
    fallbackFactory = UserClientFallbackFactory.class
)
public interface UserClient {
  @GetMapping("/detail/{userId}")
  UserResponse getUserResponse(@PathVariable Long userId);

}
