package com.example.universityadmissionscommittee.repository.examResult;

import com.example.universityadmissionscommittee.data.enums.QuotaType;
import com.example.universityadmissionscommittee.dto.ExamRowDto;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ExamResultRepositoryImpl implements ExamResultRepositoryCustom {
    private final EntityManager entityManager;

    public ExamResultRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public String examRowDataConditionWithYear(String condition, int year) {
        return """
        SELECT
            asp.id AS specialty_for_applicant_id,
            a.id AS applicant_id,
            a.first_name,
            a.last_name,
            a.phone_number,
            a.email,
            sp.id AS specialty_id,
            sp.name AS specialty_name,
            s.id AS subject_id,
            s.name AS subject_name,
            e.result,
            asp.priority,
            asp.applicant_status,
            asp.submission_date,
            b.id as benefit_id,
            b.name as benefit_name,
            b.priority_points as benefit_points
        FROM specialty sp
        JOIN subject_for_specialty sfs ON sfs.specialty_id = sp.id
        JOIN subject s ON s.id = sfs.subject_id
        
        LEFT JOIN specialty_for_applicant asp ON asp.specialty_id = sp.id
             AND EXTRACT(YEAR FROM asp.submission_date) = """ + year + """

        LEFT JOIN applicant a ON a.id = asp.applicant_id
        LEFT JOIN exam_result e ON e.applicant_id = a.id AND e.subject_id = s.id
        LEFT JOIN benefit_for_applicant bfa ON a.id = bfa.applicant_id
        LEFT JOIN benefit b ON b.id = bfa.benefit_id
        """ + "\n" + condition + "\n" + "ORDER BY sp.id, a.id, s.id";
    }


    public String examRowDataCondition(String condition) {
        return examRowDataConditionWithYear(condition, LocalDate.now().getYear());
    }

    @Override
    public List<ExamRowDto> examRowData(List<Long> specialtyIds) {
        String sql = examRowDataCondition("WHERE sp.id IN (?1)");
        return entityManager
                .createNativeQuery(sql, "ExamRowDtoMapping")
                .setParameter(1, specialtyIds)
                .getResultList();
    }

    @Override
    public List<ExamRowDto> examRowData(List<Long> specialtyIds, int year) {
        String sql = examRowDataConditionWithYear("WHERE sp.id IN (?1)", year);
        return entityManager
                .createNativeQuery(sql, "ExamRowDtoMapping")
                .setParameter(1, specialtyIds)
                .getResultList();
    }

    @Override
    public List<ExamRowDto> findExamRowsByQuota(QuotaType type) {
        String sql = examRowDataCondition("WHERE b.quota_type = ?1 OR (?1 = 'NONE' AND b.id IS NULL)");
        return entityManager
                .createNativeQuery(sql, "ExamRowDtoMapping")
                .setParameter(1, type.name())
                .getResultList();
    }

    @Override
    public List<ExamRowDto> findExamRowsByQuota(QuotaType type, int year) {
        String sql = examRowDataConditionWithYear("WHERE b.quota_type = ?1 OR (?1 = 'NONE' AND b.id IS NULL)", year);
        return entityManager
                .createNativeQuery(sql, "ExamRowDtoMapping")
                .setParameter(1, type.name())
                .getResultList();
    }

    @Override
    public List<ExamRowDto> findExamRowsByQuotaAndSpecialty(Long specialtyId, QuotaType type) {
        String sql = examRowDataCondition("""
        WHERE sp.id = ?1 
        AND (b.quota_type = ?2 OR (?2 = 'NONE' AND b.id IS NULL))
        """);
        return entityManager
                .createNativeQuery(sql, "ExamRowDtoMapping")
                .setParameter(1, specialtyId)
                .setParameter(2, type.name())
                .getResultList();
    }
}

