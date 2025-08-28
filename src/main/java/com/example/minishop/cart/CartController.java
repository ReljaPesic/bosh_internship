package com.example.minishop.cart;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;
import jakarta.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
@Tag(name = "Cart", description = "Shopping cart API")
public class CartController {

  private final CartService cartService;

  @PostMapping("/add")
  @Operation(summary = "Add a new item to a shoping cart")
  public Cart addToCart(@Valid @RequestBody CartItem item, @RequestParam Long userId) {
    return cartService.addToCart(item, userId);
  }

  @GetMapping
  @Operation(summary = "List all items in the cart")
  public List<CartItemDTO> getCartItems(@RequestParam Long userId) {
    return cartService.getCartItems(userId);
  }

  @PutMapping("/item/{id}")
  @Operation(summary = "Update specific item quantity")
  public Cart updateItemQuantity(@PathVariable Long itemId, @RequestParam Long userId,
      @Valid @RequestBody UpdateQuantityRequest quantityRequest) {
    return cartService.updateItemQuantity(itemId, userId, quantityRequest);
  }

  @DeleteMapping("/item/{id}")
  @Operation(summary = "Remove a specific item from a cart")
  public Cart removeItem(@PathVariable Long itemId, @RequestParam Long userId) {
    return cartService.removeItem(itemId, userId);
  }
}
