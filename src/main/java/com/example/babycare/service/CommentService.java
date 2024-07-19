package com.example.babycare.service;

import com.example.babycare.domain.CommentEntity;
import com.example.babycare.domain.ProgramEntity;
import com.example.babycare.domain.UserEntity;
import com.example.babycare.dto.CommentDTO;
import com.example.babycare.exception.impl.NoProgramException;
import com.example.babycare.exception.impl.NoUserException;
import com.example.babycare.repository.CommentRepository;
import com.example.babycare.repository.ProgramRepository;
import com.example.babycare.repository.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final ProgramRepository programRepository;
  private final UserRepository userRepository;

  public CommentEntity addComment(String programName, CommentDTO commentDTO, String userName) {
    ProgramEntity programEntity = programRepository.findByName(programName).orElseThrow(
        NoProgramException::new);
    UserEntity userEntity = userRepository.findByUsername(userName)
        .orElseThrow(NoUserException::new);
    return commentRepository.save(commentDTO.toEntity(userEntity, programEntity));
  }

  public CommentEntity updateComment(Long commentId, CommentDTO commentDTO, String userName) {
    Optional<CommentEntity> comment = commentRepository.findById(commentId);
    Optional<UserEntity> user = userRepository.findByUsername(userName);

    if (comment.isEmpty() || user.isEmpty() || !comment.get().getUserEntity().equals(user.get())) {
      return null;
    }

    CommentEntity commentEntity = comment.get();
    commentEntity.setContent(commentDTO.getContent());
    commentRepository.save(commentEntity);

    return commentEntity;
  }

  public void deleteComment(Long commentId, String userName) {
    Optional<CommentEntity> comment = commentRepository.findById(commentId);
    Optional<UserEntity> user = userRepository.findByUsername(userName);

    if (comment.isEmpty() || user.isEmpty() || !comment.get().getUserEntity().equals(user.get())) {
      return;
    }

    commentRepository.deleteById(commentId);
  }


}
