package com.example.minishop.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

import com.example.minishop.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {
  @Id
  @GeneratedValue
  @NotNull(message = "You have to specify the id of the cart item")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Min(value = 1, message = "You have to have at least one instance of the item")
  private int quantity;
}
