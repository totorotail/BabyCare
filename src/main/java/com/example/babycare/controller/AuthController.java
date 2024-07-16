package com.example.babycare.controller;

import com.example.babycare.dto.Auth;
import com.example.babycare.security.TokenProvider;
import com.example.babycare.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  private final TokenProvider tokenProvider;

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody Auth.SignUp request) {
    var result = this.userService.register(request);
    return ResponseEntity.ok(result);
  }

  @PostMapping("/signin")
  public ResponseEntity<?> signin(@RequestBody Auth.SignIn request) {
    var member = this.userService.authenticate(request);
    var token = this.tokenProvider.generateToken(member.getUsername(), member.getRoles());
    log.info("user login -> " + request.getUsername());
    return ResponseEntity.ok(token);
  }
}
