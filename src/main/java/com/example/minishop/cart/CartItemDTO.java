package com.example.minishop.cart;

import lombok.Data;

@Data
public class CartItemDTO {
  private Long productId;
  private int quantity;
}
