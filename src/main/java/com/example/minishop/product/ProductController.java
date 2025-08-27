package com.example.minishop.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProductController {

  private final ProductRepository productRepository;

  @GetMapping("/api/products")
  public Page<Product> getAll(@RequestParam(required = false) String name,
      @PageableDefault(size = 10, sort = "id") Pageable pageable) {
    if (name != null && !name.isEmpty()) {
      return productRepository.findByName(name, pageable);
    }
    return productRepository.findAll(pageable);
  }

  @GetMapping("/api/products/{id}")
  public Product getById(@PathVariable Long id) {
    return productRepository.findById(id).orElse(null);
  }

  @PostMapping("/api/products")
  public Product create(@Valid @RequestBody Product product) {
    return productRepository.save(product);
  }

}
