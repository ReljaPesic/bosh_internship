package com.example.minishop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.minishop.cart.Cart;
import com.example.minishop.cart.CartItem;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

  private Cart cart;
  private CartItem item1;
  private CartItem item2;

  @BeforeEach
  void setUp() {
    cart = new Cart();

    item1 = new CartItem();
    item1.setId(1L);
    item1.setQuantity(2);

    item2 = new CartItem();
    item2.setId(2L);
    item2.setQuantity(1);
  }

  @Test
  void testAddItem_NewItem() {
    cart.addItem(item1);
    assertEquals(1, cart.getItems().size());
    assertEquals(2, cart.getItems().get(0).getQuantity());
  }

  @Test
  void testAddItem_ExistingItem_IncreasesQuantity() {
    cart.addItem(item1);
    CartItem additionalItem = new CartItem();
    additionalItem.setId(1L);
    additionalItem.setQuantity(3);

    cart.addItem(additionalItem);

    assertEquals(1, cart.getItems().size());
    assertEquals(5, cart.getItems().get(0).getQuantity());
  }

  @Test
  void testRemoveItem_DecreaseQuantity() {
    cart.addItem(item1);
    cart.removeItem(1L);

    assertEquals(1, cart.getItems().size());
    assertEquals(1, cart.getItems().get(0).getQuantity());
  }

  @Test
  void testRemoveItem_RemoveCompletely() {
    cart.addItem(item2); // quantity = 1
    cart.removeItem(2L);

    assertTrue(cart.getItems().isEmpty());
  }

  @Test
  void testRemoveItem_ItemNotFound() {
    cart.addItem(item1);
    cart.removeItem(999L); // non-existent ID
    assertEquals(1, cart.getItems().size());
  }
}
