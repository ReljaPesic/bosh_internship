package com.example.minishop.cart;

import com.example.minishop.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @OneToOne
  private Product product;

  private int quantity;
}
