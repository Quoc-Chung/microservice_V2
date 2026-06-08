package com.quocchung.orderservice.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
  private Long id;
  private String name;
  private String email;
}
