package com.example.babycare.domain;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "CARTITEM")
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "cartId",
      foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "programId",
      foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ProgramEntity programEntity;

  private int quantity;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @Column
  @LastModifiedDate
  private LocalDateTime updatedAt;

  public CartItem(Cart cart, ProgramEntity programEntity, int quantity) {
    this.cart = cart;
    this.programEntity = programEntity;
    this.quantity = quantity;
  }
}
