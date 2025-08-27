package com.example.minishop.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateQuantityRequest {
  @Min(value = 1, message = "Quantity must be at least 1")
  private int quantity;
}
