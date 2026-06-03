package com.quocchung.orderservice.client;

import com.quocchung.orderservice.client.fallback.UserClientFallbackFactory;
import com.quocchung.orderservice.model.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "product-service",
    path = "/api/products",
    fallbackFactory = UserClientFallbackFactory.class
)
public interface ProductClient {
  @GetMapping("/{id}")
  ProductResponse getProduct(@PathVariable Long id);
}
