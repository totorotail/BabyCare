package com.example.babycare.service;

import com.example.babycare.domain.ProgramEntity;
import com.example.babycare.dto.ProgramDTO;
import com.example.babycare.exception.impl.NoProgramException;
import com.example.babycare.repository.ProgramRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProgramService {

  private final ProgramRepository programRepository;

  public ProgramEntity addProgram(ProgramDTO programDTO) {
    ProgramEntity program = new ProgramEntity();
    program.setName(programDTO.getName());
    program.setContent(programDTO.getContent());
    program.setTeacher(programDTO.getTeacher());
    program.setCategory(programDTO.getCategory());
    program.setPrice(programDTO.getPrice());
    return programRepository.save(program);
  }

  public String deleteProgram(String name) {
    ProgramEntity program = programRepository.findByName(name).orElseThrow(NoProgramException::new);
    this.programRepository.delete(program);
    return program.getName();
  }

  public ProgramEntity updateProgram(String name, ProgramDTO programDTO) {
    ProgramEntity program = programRepository.findByName(name).orElseThrow(NoProgramException::new);
    program.setName(programDTO.getName());
    program.setContent(programDTO.getContent());
    program.setTeacher(programDTO.getTeacher());
    program.setCategory(programDTO.getCategory());
    program.setPrice(programDTO.getPrice());
    return programRepository.save(program);
  }

  public Page<ProgramEntity> getAllPrograms(Pageable pageable) {
    return this.programRepository.findAll(pageable);
  }

  public ProgramEntity getProgramByName(String name) {
    ProgramEntity program = programRepository.findByName(name).orElseThrow(NoProgramException::new);
    return program;
  }

}
