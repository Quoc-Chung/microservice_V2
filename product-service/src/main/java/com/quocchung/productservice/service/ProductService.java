package com.quocchung.productservice.service;

import com.quocchung.productservice.model.dto.ProductResponse;
import com.quocchung.productservice.model.entity.Product;
import java.util.List;

public interface ProductService {

    List<Product> createProduct(List<Product> products);

    ProductResponse getProduct(Long id);

    List<Product> getAllProducts();

  }

