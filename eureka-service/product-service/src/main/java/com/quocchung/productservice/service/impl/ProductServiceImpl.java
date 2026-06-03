package com.quocchung.productservice.service.impl;

import com.quocchung.productservice.model.dto.ProductResponse;
import com.quocchung.productservice.model.entity.Product;
import com.quocchung.productservice.repository.ProductRepository;
import com.quocchung.productservice.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  @Override
  public List<Product> createProduct(List<Product> products) {
    return productRepository.saveAll(products);
  }

  public ProductResponse getProduct(Long id) {
    Product  product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    return ProductResponse.
        builder()
        .productId(product.getId())
        .code(product.getCode())
        .name(product.getName())
        .price(product.getPrice())
        .stock(product.getStock())
        .description(product.getDescription())
        .build();
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

}
