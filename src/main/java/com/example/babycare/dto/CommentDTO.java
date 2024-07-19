package com.example.babycare.dto;

import com.example.babycare.domain.CommentEntity;
import com.example.babycare.domain.ProgramEntity;
import com.example.babycare.domain.UserEntity;
import lombok.Data;

@Data
public class CommentDTO {

  private String content;

  public CommentEntity toEntity(UserEntity userEntity, ProgramEntity programEntity) {
    return CommentEntity.builder()
        .userEntity(userEntity)
        .programEntity(programEntity)
        .content(content)
        .build();
  }

}
