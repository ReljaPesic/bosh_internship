package com.example.minishop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.minishop.cart.Cart;
import com.example.minishop.cart.CartController;
import com.example.minishop.cart.CartItem;
import com.example.minishop.cart.CartItemDTO;
import com.example.minishop.cart.UpdateQuantityRequest;
import com.example.minishop.product.Product;
import com.example.minishop.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

class CartControllerTest {

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private CartController cartController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddToCart_NewItem() {
    CartItem item = new CartItem();
    item.setId(1L);
    item.setQuantity(2);

    Cart cart = cartController.addToCart(item);

    assertEquals(1, cart.getItems().size());
    assertEquals(2, cart.getItems().get(0).getQuantity());
  }

  @Test
  void testGetCartItems_ProductExists() {
    CartItem item = new CartItem();
    item.setId(1L);
    item.setQuantity(3);

    Product product = new Product();
    product.setId(1L);
    product.setName("Test Product");

    when(productRepository.findById(1L)).thenReturn(Optional.of(product));

    cartController.addToCart(item);
    List<CartItemDTO> items = cartController.getCartItems();

    assertEquals(1, items.size());
    assertEquals("Test Product", items.get(0).getName());
    assertEquals(3, items.get(0).getQuantity());
  }

  @Test
  void testGetCartItems_ProductNotFound() {
    CartItem item = new CartItem();
    item.setId(1L);
    item.setQuantity(1);

    cartController.addToCart(item);
    when(productRepository.findById(1L)).thenReturn(Optional.empty());

    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      cartController.getCartItems();
    });

    assertEquals("Product not found", exception.getMessage());
  }

  @Test
  void testUpdateItemQuantity() {
    CartItem item = new CartItem();
    item.setId(1L);
    item.setQuantity(2);
    cartController.addToCart(item);

    UpdateQuantityRequest request = new UpdateQuantityRequest();
    request.setQuantity(5);

    Cart cart = cartController.updateItemQuantity(1L, request);

    assertEquals(5, cart.getItems().get(0).getQuantity());
  }

  @Test
  void testRemoveItem_DecreaseQuantityOrRemove() {
    CartItem item = new CartItem();
    item.setId(1L);
    item.setQuantity(1);
    cartController.addToCart(item);

    Cart cart = cartController.removeItem(1L);

    assertTrue(cart.getItems().isEmpty());
  }
}
