package com.quocchung.inventory_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private Long productId;

  @Column(nullable = false)
  private String productCode;

  @Column(nullable = false)
  private Integer availableQty;

  @Column(nullable = false)
  private Integer reservedQty;

  @Version
  private Long version; // optimistic locking tránh racondition khi nhiều người đặt một lúc

}