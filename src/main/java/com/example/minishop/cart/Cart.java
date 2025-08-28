package com.example.minishop.cart;

import java.util.ArrayList;
import java.util.List;

import com.example.minishop.user.User;

import jakarta.persistence.*;
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

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items = new ArrayList<>();

  public void addItem(CartItem item) {
    for (CartItem cartItem : items) {
      if (cartItem.getId().equals(item.getId())) {
        cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
        return;
      }
    }
    items.add(item);
  }

  public void removeItem(Long cartItemId) {
    for (CartItem cartItem : items) {
      if (cartItem.getId().equals(cartItemId)) {
        items.remove(cartItem);
      }
      return;
    }
  }
}
