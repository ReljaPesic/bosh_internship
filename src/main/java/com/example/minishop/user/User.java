package com.example.minishop.user;

import com.example.minishop.cart.Cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;

  @OneToOne(cascade = CascadeType.ALL)
  private Cart cart;

  @Enumerated(EnumType.STRING)
  private Role role;
}
