package com.example.minishop.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductLoader implements CommandLineRunner {

  private final ProductRepository productRepository;

  @Override
  public void run(String... args) throws Exception {
    if (productRepository.count() == 0) {
      Product product1 = new Product(null, "Laptop", "A high-performance laptop", 999.99);
      Product product2 = new Product(null, "Smartphone", "A latest model smartphone", 699.99);
      productRepository.save(product1);
      productRepository.save(product2);
    }
  }
}
