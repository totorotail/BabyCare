package com.example.babycare.repository;

import com.example.babycare.domain.ProgramEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<ProgramEntity, Long> {

  Optional<ProgramEntity> findByName(String name);

}
