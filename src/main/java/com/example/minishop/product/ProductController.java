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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Tag(name = "Products", description = "Product management API")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/api/products")
  @Operation(summary = "List all products")
  public Page<Product> getAll(@RequestParam(required = false) String name,
      @PageableDefault(size = 10, sort = "id") Pageable pageable) {
    return productService.getAll(name, pageable);
  }

  @GetMapping("/api/products/{id}")
  @Operation(summary = "Get specific product by id")
  public Product getById(@PathVariable Long id) {
    return productService.getById(id);
  }

  @PostMapping("/api/products")
  @Operation(summary = "Add new product")
  public Product create(@Valid @RequestBody Product product) {
    return productService.create(product);
  }
}
