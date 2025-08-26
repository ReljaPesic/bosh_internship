package com.example.minishop.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProductController {

  private final ProductRepository productRepository;

  @GetMapping("/api/products")
  public Iterable<Product> getAll() {
    return productRepository.findAll();
  }

  @GetMapping("/api/products/{id}")
  public Product getById(@PathVariable Long id) {
    return productRepository.findById(id).orElse(null);
  }

  @PostMapping("/api/products")
  public Product create(@RequestBody Product product) {
    return productRepository.save(product);
  }

}
