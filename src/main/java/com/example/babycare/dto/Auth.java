package com.example.babycare.dto;

import com.example.babycare.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

public class Auth {

  @Data
  public static class SignIn {

    private String username;
    private String password;
  }

  @Data
  public static class SignUp {

    private String username;
    private String password;
    private String email;
    private List<String> roles;

    public User toEntity() {
      return User.builder().username(this.username)
          .password(this.password)
          .email(this.email)
          .roles(this.roles)
          .build();
    }

  }

}
