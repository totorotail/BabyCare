package com.example.babycare.security;

import com.example.babycare.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class TokenProvider {

  private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60; // 1시간
  private static final String KEY_ROLES = "roles";

  private final UserService userService;

  @Value("${jwt.secret}")
  private String secretKey;

  public String generateToken(String username, List<String> roles) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put(KEY_ROLES, roles);

    var now = new Date();
    var exporedDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(exporedDate)
        .signWith(SignatureAlgorithm.HS512, this.secretKey) // 사용할 알고리즘, 비밀키
        .compact();

  }

  public Authentication getAuthentication(String jwt) {
    UserDetails userDetails = this.userService.loadUserByUsername(this.getUsername(jwt));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return this.parseClaims(token).getSubject();
  }

  public boolean validateToken(String token) {
    if (!StringUtils.hasText(token)) {
      return false;
    }

    var claims = this.parseClaims(token);
    return !claims.getExpiration().before(new Date());
  }

  private Claims parseClaims(String token) {

    try {
      return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }

}
