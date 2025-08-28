package com.example.minishop.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping
  public List<User> getAllUsers() {
    return userService.findAll();
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
    if (userService.userExists(request.getUsername())) {
      return ResponseEntity.badRequest().body("Username already exists");
    }
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(request.getPassword());

    userService.saveUser(user);
    return ResponseEntity.ok("User registered successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<?> authUser(@RequestBody RegisterRequest request) {
    User user = userService.findByUsername(request.getUsername()).orElse(null);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
    }

    if (!user.getPassword().equals(request.getPassword())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
    }

    return ResponseEntity.ok("Login successful for user: " + user.getUsername());
  }
}
