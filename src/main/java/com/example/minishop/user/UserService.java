package com.example.minishop.user;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public User findById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User with id: " + userId + " not found"));
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }

  public boolean userExists(String username) {
    return userRepository.existsByUsername(username);
  }
}
