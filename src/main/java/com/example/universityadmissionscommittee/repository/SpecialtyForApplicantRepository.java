package com.example.universityadmissionscommittee.repository;

import com.example.universityadmissionscommittee.data.SpecialtyForApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyForApplicantRepository extends JpaRepository<SpecialtyForApplicant, Long> {

    @Override
    Optional<SpecialtyForApplicant> findById(Long id);
}