package com.example.minishop.cart;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items = new ArrayList<>();

  public void addItem(CartItem item) {
    for (CartItem cartItem : items) {
      if (cartItem.getProductId().equals(item.getProductId())) {
        cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
        return;
      }
    }
    items.add(item);
  }

  public void removeItem(Long productId) {
    for (CartItem cartItem : items) {
      if (cartItem.getProductId().equals(productId)) {
        if (cartItem.getQuantity() > 1) {
          cartItem.setQuantity(cartItem.getQuantity() - 1);
        } else {
          items.remove(cartItem);
        }
        return;
      }
    }
  }
}
