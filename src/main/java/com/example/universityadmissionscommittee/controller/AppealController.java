package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.dto.appeal.AppealInitDto;
import com.example.universityadmissionscommittee.dto.appeal.AppealReportGrouped;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyInitDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportGrouped;
import com.example.universityadmissionscommittee.service.AppealService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appeals")
public class AppealController {
    private SpecialtyService specialtyService;

    private AppealService appealService;

    public AppealController(AppealService appealService,
                            SpecialtyService specialtyService) {
        this.appealService = appealService;
        this.specialtyService = specialtyService;
    }

    @GetMapping("/initializeAppealPage")
    public AppealInitDto initialize() {
        return new AppealInitDto(specialtyService.allIdAndName());
    }

    @GetMapping("/filterAppealsBySpecialty/{specialtyId}")
    public AppealReportGrouped selectSpecialty(@PathVariable Long specialtyId) {
        return appealService.appealsByOneSpecialty(specialtyId);
    }
}
