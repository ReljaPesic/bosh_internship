package com.example.minishop.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public class ProductService {

  private final ProductRepository productRepository;

  public Page<Product> getAll(String name, Pageable pageable) {
    if (name != null && !name.isEmpty()) {
      return productRepository.findByName(name, pageable);
    }
    return productRepository.findAll(pageable);
  }

  public Product getById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product with id: " + id + " not found"));
  }

  public Product create(Product product) {
    return productRepository.save(product);
  }
}
