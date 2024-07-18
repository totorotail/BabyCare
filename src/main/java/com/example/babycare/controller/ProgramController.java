package com.example.babycare.controller;

import com.example.babycare.domain.ProgramEntity;
import com.example.babycare.dto.ProgramDTO;
import com.example.babycare.service.ProgramService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/program")
@AllArgsConstructor
public class ProgramController {

  private final ProgramService programService;

  @PostMapping("/add")
  @PreAuthorize("hasRole('ADMIN')")
  public ProgramEntity addProgram(@RequestBody ProgramDTO programDTO) {
    return programService.addProgram(programDTO);
  }

  @DeleteMapping("/delete/{name}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> deleteProgram(@PathVariable("name") String name) {
    String programName = programService.deleteProgram(name);
    return ResponseEntity.ok(programName);
  }

  @PutMapping("/update/{name}")
  @PreAuthorize("hasRole('ADMIN')")
  public ProgramEntity updateProgram(@PathVariable("name") String name,
      @RequestBody ProgramDTO programDTO) {
    return programService.updateProgram(name, programDTO);
  }

  @GetMapping("/getAll")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> getAllPrograms(final Pageable pageable) {
    Page<ProgramEntity> programs = programService.getAllPrograms(pageable);
    return ResponseEntity.ok(programs);
  }

  @GetMapping("/search/{name}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> getProgramByName(@PathVariable("name") String name) {
    ProgramEntity programEntity = programService.getProgramByName(name);
    return ResponseEntity.ok(programEntity);
  }

}
