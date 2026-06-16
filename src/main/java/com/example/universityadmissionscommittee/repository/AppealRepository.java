package com.example.universityadmissionscommittee.repository;

import com.example.universityadmissionscommittee.data.Appeal;
import com.example.universityadmissionscommittee.data.enums.AppealStatus;
import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import com.example.universityadmissionscommittee.data.enums.QuotaType;
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

    @Query("""
    SELECT DISTINCT new com.example.universityadmissionscommittee.dto.appeal.AppealReportDto(
        a.id,
        app.id,
        app.firstName,
        sp.id,
        sp.name,
        sfa.id,
        sfa.applicantStatus,
        a.appealStatus,
        a.message
    )
    FROM Appeal a
    LEFT JOIN a.specialtyForApplicant sfa
    LEFT JOIN sfa.applicant app
    LEFT JOIN sfa.specialty sp
    LEFT JOIN app.benefits b
    WHERE (?1 IS NULL OR sp.id = ?1)
      AND (?2 IS NULL OR YEAR(sfa.date) = ?2)
      AND (
          ?3 IS NULL
          OR b.type = ?3
          OR (CAST(?3 AS string) = 'NONE' AND b IS NULL)
      )
    """)
    List<AppealReportDto> appealsBySpecialtyQuotaYear(
            Long specialtyId,
            Integer year,
            QuotaType quotaType
    );



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
        where a.id = :appealId
    """)
    List<AppealReportDto> findAppealById(@Param("appealId") Long appealId);
}