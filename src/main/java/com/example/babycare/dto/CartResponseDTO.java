package com.example.babycare.dto;

import com.example.babycare.domain.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartResponseDTO {

  private Long cartItemId;
  private String name;
  private int InsertQuantity;
  private Long price;

  public static CartResponseDTO toDTO(CartItem cartItem, String name, Long price) {
    return new CartResponseDTO(cartItem.getId(), name, cartItem.getQuantity(), price);
  }
}
