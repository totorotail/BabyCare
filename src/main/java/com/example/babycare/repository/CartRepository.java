package com.example.babycare.repository;

import com.example.babycare.domain.Cart;
import com.example.babycare.domain.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByUserEntity(UserEntity userEntity);

}
