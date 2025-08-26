package com.example.minishop.cart;

import com.example.minishop.product.Product;

import lombok.Data;

@Data
public class CartItemDTO {
  private Long id;
  private String name;
  private Double price;
  private int quantity;

  public CartItemDTO(Product product, int quantity) {
    this.id = product.getId();
    this.name = product.getName();
    this.price = product.getPrice();
    this.quantity = quantity;
  }
}
