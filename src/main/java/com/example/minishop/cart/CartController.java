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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;
import jakarta.validation.Valid;
import java.lang.RuntimeException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
@Tag(name = "Cart", description = "Shopping cart API")
public class CartController {

  private final Cart cart = new Cart();
  private final ProductRepository productRepository;

  @PostMapping("/add")
  @Operation(summary = "Add a new item to a shoping cart")
  public Cart addToCart(@Valid @RequestBody CartItem item) {
    cart.addItem(item);
    return cart;
  }

  @GetMapping
  @Operation(summary = "List all items in the cart")
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
  @Operation(summary = "Update specific item quantity")
  public Cart updateItemQuantity(@PathVariable Long id, @Valid @RequestBody UpdateQuantityRequest quantityRequest) {
    cart.getItems().stream()
        .filter(i -> i.getId().equals(id))
        .findFirst()
        .ifPresent(i -> i.setQuantity(quantityRequest.getQuantity()));
    return cart;
  }

  @DeleteMapping("/item/{id}")
  @Operation(summary = "Remove a specific item from a cart")
  public Cart removeItem(@PathVariable Long id) {
    cart.removeItem(id);
    return cart;
  }
}
