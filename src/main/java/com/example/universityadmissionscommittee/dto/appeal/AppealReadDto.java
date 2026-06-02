package com.example.universityadmissionscommittee.dto.appeal;

public record AppealReadDto(
        Long applicantId,
        String fullName,
        Long specialtyForApplicantId,
        String appealText
) {
}
