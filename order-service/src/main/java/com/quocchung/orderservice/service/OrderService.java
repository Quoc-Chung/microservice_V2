package com.quocchung.orderservice.service;

import com.quocchung.orderservice.model.dto.CreateOrderRequest;
import com.quocchung.orderservice.model.dto.OrderCreateResponse;

public interface OrderService {
  OrderCreateResponse createOrder(CreateOrderRequest orderRequest);
}
