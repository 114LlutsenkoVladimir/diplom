package com.example.universityadmissionscommittee.dto.appeal;

import com.example.universityadmissionscommittee.data.enums.AppealStatus;
import com.example.universityadmissionscommittee.data.enums.QuotaType;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyIdAndNameDto;

import java.util.List;

public record AppealInitDto(
    List<SpecialtyIdAndNameDto> allSpecialties,
    List<AppealStatus> allAppealStatuses,
    List<QuotaType> allQuotaTypes
){}

