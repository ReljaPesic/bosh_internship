package com.example.minishop.cart;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.minishop.product.Product;
import com.example.minishop.product.ProductService;
import com.example.minishop.user.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private ProductService productService;
  private UserService userService;

  public Cart addToCart(CartItem cartItem, Long userId) {
    User user = userService.findById(userId);
    Cart cart = user.getCart();

    cart.addItem(cartItem);
    cartRepository.save(cart);
    return cart;
  }

  public List<CartItemDTO> getCartItems(Long userId) {
    User user = userService.findById(userId);
    Cart cart = user.getCart();

    return cart.getItems().stream()
        .map(item -> new CartItemDTO(product, item.getQuantity()))
        .toList();
  }

  public Cart updateItemQuantity(Long itemId, Long userId, UpdateQuantityRequest quantityRequest) {
    User user = userService.findById(userId);
    Cart cart = user.getCart();

    cart.getItems().stream()
        .filter(i -> i.getId().equals(id))
        .findFirst()
        .ifPresent(i -> i.setQuantity(quantityRequest.getQuantity()));
    return cart;
  }

  public Cart removeItem(Long itemId, Long userId) {
    User user = userService.findById(userId);
    Cart cart = user.getCart();

    cart.removeItem(itemId);
    return cart;
  }

  private Cart createCartForUser(Long userId) {
    User user = userService.findById(userId);

    Cart cart = new Cart();
    cart.setUser(user);
    return cartRepository.save(cart);
  }
}
