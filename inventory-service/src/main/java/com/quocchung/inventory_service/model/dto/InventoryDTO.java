package com.quocchung.inventory_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDTO {
  private Long id;
  private Long productId;
  private String productCode;
  private Integer availableQty;
  private Integer reservedQty;
}
