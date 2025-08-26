package com.example.minishop.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @GetMapping("/api/products")
  public Iterable<Product> getAll() {
    return java.util.List.of(
        new Product(1L, "Product 1", "Description 1", 10.0),
        new Product(2L, "Product 2", "Description 2", 20.0));
  }
}
