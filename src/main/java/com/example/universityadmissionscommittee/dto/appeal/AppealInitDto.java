package com.example.universityadmissionscommittee.dto.appeal;

import com.example.universityadmissionscommittee.dto.specialty.SpecialtyIdAndNameDto;

import java.util.List;

public record AppealInitDto(
    List<SpecialtyIdAndNameDto> allSpecialties
){}

