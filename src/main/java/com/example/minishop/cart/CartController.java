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

import com.example.minishop.product.Product;
import com.example.minishop.product.ProductRepository;

import lombok.AllArgsConstructor;

import java.lang.RuntimeException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

  private final Cart cart = new Cart();
  private final ProductRepository productRepository;

  @PostMapping("/add")
  public Cart addToCart(@RequestBody CartItem item) {
    cart.addItem(item);
    return cart;
  }

  @GetMapping
  public List<CartItemDTO> getCartItems() {
    return cart.getItems().stream()
        .map(item -> {
          Product product = productRepository.findById(item.getId())
              .orElseThrow(() -> new RuntimeException("Product not found"));
          return new CartItemDTO(product, item.getQuantity());
        })
        .toList();
  }

  @PutMapping("/item/{id}")
  public Cart updateItemQuantity(@PathVariable Long id, @RequestBody int quantity) {
    cart.getItems().stream()
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
