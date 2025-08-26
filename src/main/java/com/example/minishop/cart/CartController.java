package com.example.minishop.cart;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

  private final Cart cart = new Cart();

  @PostMapping("/add")
  public Cart addToCart(@RequestBody CartItem item) {
    cart.addItem(item);
    return cart;
  }

  @GetMapping
  public List<CartItem> getCartItems() {
    return cart.getItems();
  }

  @PutMapping("/item/{id}")
  public Cart updateItemQuantity(@PathVariable Long id, @RequestBody int quantity) {
    cart.getItems().steam()
        .filter(i -> i.getId().equals(id))
        .findFirst()
        .ifPresent(i -> i.setQuantity(quantity));
    return cart;
  }

  @DeleteMapping("/item/{id}")
  public Cart removeItem(@PathVariable Long id) {
    cart.removeItem(id);
    return cart;
  }
}
