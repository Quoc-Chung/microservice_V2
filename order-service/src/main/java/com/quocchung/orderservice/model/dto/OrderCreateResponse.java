package com.quocchung.orderservice.model.dto;

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
public class OrderCreateResponse {
   private UserCreateResponse userCreateResponse;

   private List<OrderCreateItem> orderCreateItemList;

   private LocalDateTime createDate;

   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   @Builder
   public static class UserCreateResponse{
     private Long userId;
     private String name;
     private String email;

   }

   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   @Builder
   public static class OrderCreateItem{
        private Long productId;
        private String productCode;
        private String productName;
        private Integer quantity;
   }

   private Double totalAmount;
}
