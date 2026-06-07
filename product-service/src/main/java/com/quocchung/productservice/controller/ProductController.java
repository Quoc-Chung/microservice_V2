package com.quocchung.productservice.controller;

import com.quocchung.productservice.model.dto.ProductResponse;
import com.quocchung.productservice.model.entity.Product;
import com.quocchung.productservice.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public List<Product> listProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public ProductResponse getProduct(@PathVariable Long id) {
    return productService.getProduct(id);
  }

  @PostMapping
  public List<Product> create(@RequestBody List<Product> product) {
    return productService.createProduct(product);
  }

}