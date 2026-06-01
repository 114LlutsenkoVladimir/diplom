package com.example.universityadmissionscommittee.repository.examResult;

import com.example.universityadmissionscommittee.data.enums.QuotaType;
import com.example.universityadmissionscommittee.dto.ExamRowDto;

import java.util.List;

public interface ExamResultRepositoryCustom {
    List<ExamRowDto> examRowData(List<Long> specialtyIds);

    List<ExamRowDto> findExamRowsByQuota(QuotaType type);

    List<ExamRowDto> findExamRowsByQuotaAndSpecialty(Long specialtyId, QuotaType type);
}
