package com.example.babycare.service;

import com.example.babycare.domain.Cart;
import com.example.babycare.domain.CartItem;
import com.example.babycare.domain.ProgramEntity;
import com.example.babycare.domain.UserEntity;
import com.example.babycare.dto.CartRequestDTO;
import com.example.babycare.dto.CartResponseDTO;
import com.example.babycare.exception.impl.NoCartException;
import com.example.babycare.exception.impl.NoCartItemException;
import com.example.babycare.exception.impl.NoProgramException;
import com.example.babycare.exception.impl.UserNotEqualsException;
import com.example.babycare.repository.CartItemRepository;
import com.example.babycare.repository.CartRepository;
import com.example.babycare.repository.ProgramRepository;
import com.example.babycare.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProgramRepository programRepository;
  private final UserRepository userRepository;

  public void addCart(CartRequestDTO cartRequestDTO, UserEntity userEntity) {
    ProgramEntity programEntity = programRepository.findByName(cartRequestDTO.getProgramName())
        .orElseThrow(NoProgramException::new);

    if (cartRepository.findByUserEntity(userEntity).isEmpty()) {
      Cart cart = new Cart(userEntity);
      cartRepository.save(cart);
    }

    Cart cart = cartRepository.findByUserEntity(userEntity).get();

    CartItem cartItem = new CartItem(cart, programEntity, cartRequestDTO.getQuantity());
    cartItemRepository.save(cartItem);
  }

  public List<CartResponseDTO> findAll(UserEntity userEntity) {
    Cart cart = cartRepository.findByUserEntity(userEntity).orElseThrow(NoCartException::new);

    List<CartItem> items = cartItemRepository.findAllByCart(cart);
    List<CartResponseDTO> result = new ArrayList<>();

    for (CartItem item : items) {
      ProgramEntity programEntity = item.getProgramEntity();
      result.add(CartResponseDTO.toDTO(item, programEntity.getName(), programEntity.getPrice()));
    }

    return result;
  }

  public void deleteById(Long id, UserEntity userEntity) {
    CartItem cartItem = cartItemRepository.findById(id).orElseThrow(NoCartItemException::new);

    Cart cart = cartItem.getCart();

    if (!cart.getUserEntity().equals(userEntity)) {
      throw new UserNotEqualsException();
    }

    cartItemRepository.delete(cartItem);
  }

}
