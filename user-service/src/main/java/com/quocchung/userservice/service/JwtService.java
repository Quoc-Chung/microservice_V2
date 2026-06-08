package com.quocchung.userservice.service;

import com.quocchung.userservice.model.entity.User;
import io.jsonwebtoken.Claims;

public interface JwtService {
  String generateToken(User user);

  Claims extractClaims(String token);

  boolean isTokenValid(String token);

  Long extractUserId(String token);
}
