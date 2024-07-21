package com.example.babycare.controller;

import com.example.babycare.domain.UserEntity;
import com.example.babycare.dto.CommentDTO;
import com.example.babycare.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/add/{programName}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> addComment(@PathVariable("programName") String programName,
      @RequestBody CommentDTO commentDTO) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserEntity user = (UserEntity) auth.getPrincipal();
    commentService.addComment(programName, commentDTO, user.getUsername());
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PutMapping("/update/{commentId}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId,
      @RequestBody CommentDTO commentDTO) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserEntity user = (UserEntity) auth.getPrincipal();
    commentService.updateComment(commentId, commentDTO, user.getUsername());
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @DeleteMapping("/delete/{commentId}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserEntity user = (UserEntity) auth.getPrincipal();
    commentService.deleteComment(commentId, user.getUsername());
    return ResponseEntity.ok(HttpStatus.OK);
  }


}
