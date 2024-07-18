package com.example.babycare.dto;

import com.example.babycare.domain.UserEntity;
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

    public UserEntity toEntity() {
      return UserEntity.builder().username(this.username)
          .password(this.password)
          .email(this.email)
          .roles(this.roles)
          .build();
    }

  }

}
