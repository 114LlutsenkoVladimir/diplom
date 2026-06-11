package com.example.universityadmissionscommittee.repository;

import com.example.universityadmissionscommittee.data.Appeal;
import com.example.universityadmissionscommittee.data.enums.AppealStatus;
import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import com.example.universityadmissionscommittee.dto.appeal.AppealReportDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppealRepository extends JpaRepositoryImplementation<Appeal, Long> {
    @Query("""
        SELECT new com.example.universityadmissionscommittee.dto.appeal.AppealReportDto(
            a.id,
            a.specialtyForApplicant.applicant.id,
            a.specialtyForApplicant.applicant.firstName,
            a.specialtyForApplicant.specialty.id,
            a.specialtyForApplicant.specialty.name,
            a.specialtyForApplicant.id,
            a.specialtyForApplicant.applicantStatus,
            a.appealStatus,
            a.message
        )
        FROM Appeal a
        LEFT JOIN a.specialtyForApplicant.specialty s
        WHERE s.id IN :specialtyIds
    """)
    List<AppealReportDto> appealsBySpecialties(@Param("specialtyIds") List<Long> specialtyIds);
}