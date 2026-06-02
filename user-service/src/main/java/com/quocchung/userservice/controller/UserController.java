package com.quocchung.userservice.controller;
import com.quocchung.userservice.model.dto.UserResponse;
import com.quocchung.userservice.model.entity.User;
import com.quocchung.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

  private final UserRepository userRepository;

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/detail/{userId}")
  @Cacheable(value = "products", key = "#userId")
  public UserResponse getUserResponse(@PathVariable Long userId){
      log.info("Dữ liệu được lấy từ database");
      User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
      if(user == null){
        return null;
      }
      return UserResponse.builder()
          .id(user.getId())
          .email(user.getEmail())
          .name(user.getName())
          .build();
  }
}