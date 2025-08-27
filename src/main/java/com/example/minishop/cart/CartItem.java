package com.example.minishop.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {
  @Id
  @NotNull(message = "You have to specify the id of the cart item")
  private Long id;

  @Min(value = 1, message = "You have to have at least one instance of the item")
  private int quantity;
}
