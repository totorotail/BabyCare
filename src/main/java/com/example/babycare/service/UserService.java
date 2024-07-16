package com.example.babycare.service;

import com.example.babycare.domain.User;
import com.example.babycare.dto.Auth;
import com.example.babycare.exception.impl.AlreadyExistUserException;
import com.example.babycare.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + username));
  }

  public User register(Auth.SignUp user) {
    boolean exists = this.userRepository.existsByUsername(user.getUsername());
    if (exists) {
      throw new AlreadyExistUserException();
    }

    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    var result = this.userRepository.save(user.toEntity());

    return result;
  }

  public User authenticate(Auth.SignIn user) {

    var result = this.userRepository.findByUsername(user.getUsername())
        .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다."));
    if (!this.passwordEncoder.matches(user.getPassword(), result.getPassword())) {
      throw new RuntimeException("비밀번호가 일치하지 않습니다.");
    }

    return result;
  }
}
