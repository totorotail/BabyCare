package com.example.babycare.repository;

import com.example.babycare.domain.Cart;
import com.example.babycare.domain.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  List<CartItem> findAllByCart(Cart cart);
}
