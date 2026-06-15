package com.example.universityadmissionscommittee.repository;

import com.example.universityadmissionscommittee.data.SpecialtyForApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpecialtyForApplicantRepository extends JpaRepository<SpecialtyForApplicant, Long> {

    @Override
    Optional<SpecialtyForApplicant> findById(Long id);

    @Query("SELECT DISTINCT YEAR(s.date) FROM SpecialtyForApplicant s ORDER BY YEAR(s.date) ASC")
    List<Integer> findAllSubmissionYears();
}