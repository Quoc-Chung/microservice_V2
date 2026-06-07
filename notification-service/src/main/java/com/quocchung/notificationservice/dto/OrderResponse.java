package com.quocchung.notificationservice.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
  private Long id;

  private String product;

  private Double price;

  private UserResponse userResponse;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class UserResponse{
    private Long id;

    private String name;

    private String email ;

  }
}
