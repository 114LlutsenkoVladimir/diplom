package com.example.universityadmissionscommittee.dto.appeal;

import com.example.universityadmissionscommittee.data.enums.AppealStatus;
import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;

public record AppealReportDto(
        Long appealId,
        Long applicantId,
        String fullName,
        Long specialtyId,
        String specialtyName,
        Long specialtyForApplicantId,
        ApplicantStatus applicantStatus,
        AppealStatus appealStatus,
        String message
) {
    public String getAppealStatus() {return appealStatus.getName();}
    public String getApplicantStatus() {return applicantStatus.getName();}
}
