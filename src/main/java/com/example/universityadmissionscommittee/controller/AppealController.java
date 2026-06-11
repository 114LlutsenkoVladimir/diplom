package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.data.enums.AppealStatus;
import com.example.universityadmissionscommittee.dto.appeal.AppealInitDto;
import com.example.universityadmissionscommittee.dto.appeal.AppealReportGrouped;
import com.example.universityadmissionscommittee.service.AppealService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        return new AppealInitDto(specialtyService.allIdAndName(),
                new ArrayList<>(List.of(AppealStatus.values())));
    }

    @GetMapping("/filterAppealsBySpecialty/{specialtyId}")
    public AppealReportGrouped selectSpecialty(@PathVariable Long specialtyId) {
        return appealService.appealsByOneSpecialty(specialtyId);
    }

    @GetMapping("/updateAppealStatus")
    public void updateAppealStatus(@RequestParam AppealStatus status,
                                   @RequestParam Long appealId) {
        appealService.updateAppealStatus(appealId, status);
    }
}
