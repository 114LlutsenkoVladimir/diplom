package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.data.Appeal;
import com.example.universityadmissionscommittee.data.enums.AppealStatus;
import com.example.universityadmissionscommittee.data.enums.QuotaType;
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
                new ArrayList<>(List.of(AppealStatus.values())),
                new ArrayList<>(List.of(QuotaType.values())));
    }

    @GetMapping("/filterAppealsBySpecialtyAndQuota")
    public AppealReportGrouped selectSpecialty(@RequestParam(required = false) Long specialtyId,
                                               @RequestParam(required = false) QuotaType type) {
        return appealService.appealsBySpecialtyAndQuota(specialtyId, type);
    }

    @GetMapping("/updateAppealStatus")
    public void updateAppealStatus(@RequestParam AppealStatus status,
                                   @RequestParam Long appealId) {
        appealService.updateAppealStatus(appealId, status);
    }

    @PostMapping("/addAppeal")
    public Appeal addAppeal(@RequestParam Long specialtyForApplicantId,
                            @RequestParam String appealMessage) {
        return appealService.create(specialtyForApplicantId, appealMessage);
    }

    @GetMapping("/findById/{appealId}")
    public AppealReportGrouped findById(@PathVariable Long appealId) {
        return appealService.findByIdDto(appealId);
    }

    @DeleteMapping("/deleteById/{appealId}")
    public void deleteById(@PathVariable Long appealId) {
        appealService.deleteById(appealId);
    }
}
