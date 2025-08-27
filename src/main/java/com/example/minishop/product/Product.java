package com.example.minishop.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "Name is mandatory, it cannot be an empty string")
  private String name;

  @Size(max = 500, message = "Description cannot be longer than 500 characters")
  private String description;

  @NotNull(message = "Price is required")
  @Positive(message = "Price must be greater than 0")
  private Double price;

}
