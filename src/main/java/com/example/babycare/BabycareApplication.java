package com.example.babycare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BabycareApplication {

  public static void main(String[] args) {
    SpringApplication.run(BabycareApplication.class, args);
  }

}
