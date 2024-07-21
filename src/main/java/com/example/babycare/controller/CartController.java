package com.example.babycare.controller;

import com.example.babycare.domain.UserEntity;
import com.example.babycare.dto.CartRequestDTO;
import com.example.babycare.exception.impl.NoUserException;
import com.example.babycare.repository.UserRepository;
import com.example.babycare.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

  private final CartService cartService;
  private final UserRepository userRepository;

  @PostMapping("/add")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> addCart(@RequestBody CartRequestDTO cartRequestDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserEntity userEntity = userRepository.findByUsername(authentication.getName()).orElseThrow(
        NoUserException::new);
    cartService.addCart(cartRequestDTO, userEntity);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @GetMapping("/get")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> findAll() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserEntity userEntity = userRepository.findByUsername(authentication.getName()).orElseThrow(
        NoUserException::new);
    cartService.findAll(userEntity);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @DeleteMapping("/delete/{cartItemId}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> deleteById(@PathVariable("cartItemId") Long id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserEntity userEntity = userRepository.findByUsername(authentication.getName()).orElseThrow(
        NoUserException::new);
    cartService.deleteById(id, userEntity);
    return ResponseEntity.ok(HttpStatus.OK);
  }


}
