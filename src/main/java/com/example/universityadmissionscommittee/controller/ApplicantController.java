package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.data.*;
import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import com.example.universityadmissionscommittee.data.enums.QuotaType;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantCreateDto;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantInitDto;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantReportGrouped;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyIdAndNameDto;
import com.example.universityadmissionscommittee.exception.applicant.ApplicantNotFoundException;
import com.example.universityadmissionscommittee.exception.specialty.SpecialtyNotFoundException;
import com.example.universityadmissionscommittee.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/applicants")
public class ApplicantController implements GetApplicantId {
    private ApplicantService applicantService;
    private SpecialtyService specialtyService;
    private SubjectService subjectService;
    private BenefitService benefitService;
    private AppealService appealService;
    private SpecialtyForApplicantService specialtyForApplicantService;

    public ApplicantController(ApplicantService applicantService,
                               SpecialtyService specialtyService,
                               SubjectService subjectService,
                               BenefitService benefitService,
                               AppealService appealService,
                               SpecialtyForApplicantService specialtyForApplicantService) {
        this.applicantService = applicantService;
        this.specialtyService = specialtyService;
        this.subjectService = subjectService;
        this.benefitService = benefitService;
        this.appealService = appealService;
        this.specialtyForApplicantService = specialtyForApplicantService;
    }

    @PostMapping("/addApplicant")
    public ApplicantReportGrouped addApplicant(@RequestBody ApplicantCreateDto dto) {
        Applicant applicant = applicantService.createApplicantFromDto(dto);
        Long id = applicant.getId();
        return applicantService.findApplicantByKeyAttributes(id, null, null);
    }

    @GetMapping("/getUserApplicantId")
    public Long getUserApplicantId(HttpSession session) {
        return getApplicantId(session);
    }

    @GetMapping("/initializeApplicantPage")
    private ApplicantInitDto initialize() {
        return new ApplicantInitDto(
                benefitService.allIdAndName(),
                subjectService.allIdAndName(),
                specialtyService.allIdAndName(),
                Arrays.stream(QuotaType.values()).toList(),
                Arrays.stream(ApplicantStatus.values()).toList(),
                specialtyForApplicantService.findAllSubmissionYears()
        );
    }

    private ApplicantReportGrouped updateTable(Long specialtyId) {
        return applicantService.getApplicantsByOneSpecialty(specialtyId);
    }


    @DeleteMapping("/deleteApplicant/{id}")
    public ResponseEntity<Void> deleteApplicantById(@PathVariable Long id) {
        applicantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/availableSpecialties")
    public List<SpecialtyIdAndNameDto> availableSpecialties(@RequestBody List<Long> subjectIds) {
        return specialtyService.findAvailableForSubjects(subjectIds);
    }

    @PostMapping("/updateApplicant")
    public Applicant updateApplicant(@RequestParam Long id,
                                  @RequestParam(required = false) String firstName,
                                  @RequestParam(required = false) String lastName,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) String phoneNumber,
                                  @RequestParam ApplicantStatus statusType,
                                  HttpSession session) {

        Applicant applicant = applicantService.findById(id);
        if(!firstName.isEmpty())
            applicant.setFirstName(firstName);
        if(!lastName.isEmpty())
            applicant.setLastName(lastName);
        if(!email.isEmpty())
            applicant.setEmail(email);
        if(!phoneNumber.isEmpty())
            applicant.setPhoneNumber(phoneNumber);
//        applicant.setStatusType(statusType);
//        applicant.setStatus(statusType.toString());
        return applicantService.save(applicant);
    }

    @GetMapping("/findApplicant")
    public ApplicantReportGrouped findApplicant(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            HttpSession session
    ) {
        Long appId = getApplicantId(session);
        if(appId < 0)
            return applicantService.findApplicantByKeyAttributes(appId, null, null);
        return applicantService.findApplicantByKeyAttributes(id, email, phoneNumber);
    }

    @GetMapping("/updateApplicantStatus")
    public ApplicantReportGrouped updateApplicantStatus(
            @RequestParam Long applicantId,
            @RequestParam Long specialtyId,
            @RequestParam ApplicantStatus status
    ) {
        applicantService.updateApplicantStatus(applicantId, specialtyId, status);
        return applicantService.findApplicantByKeyAttributes(applicantId, null, null);
    }


    @GetMapping("/filterApplicantsBySpecialty/{specialtyId}")
    public ApplicantReportGrouped filterApplicants(@PathVariable Long specialtyId) {
        return updateTable(specialtyId);
    }

    @GetMapping("/findApplicantsByQuota")
    public ApplicantReportGrouped findApplicantsByQuota(@RequestParam QuotaType type) {
        return applicantService.getApplicantsByQuota(type);
    }

    @GetMapping("/findApplicantsByQuotaAndSpecialty")
    public ApplicantReportGrouped findApplicantsByQuotaAndSpecialty(@RequestParam QuotaType type,
                                                                    @RequestParam Long specialtyId) {
        return applicantService.getApplicantsBySpecialtyAndQuotaType(specialtyId, type);
    }


    @GetMapping("/deleteAppeal/{id}")
    public void deleteAppeal(@PathVariable Long id) {
        appealService.deleteById(id);
    }

    @GetMapping("/findAppeal/{id}")
    public void findAppeal(@PathVariable Long id) {
        appealService.findById(id);
    }

    @DeleteMapping("/deleteSpecialtyForApplicant/{id}")
    public ResponseEntity<Void> deleteSpecialtyForApplicant(@PathVariable Long id,
                                            HttpSession session) {
        Long appId = getApplicantId(session);
        SpecialtyForApplicant application = specialtyForApplicantService.findById(id);
        if(application == null || (appId > 0
                && !application.getApplicant().getId().equals(appId)))
            throw new ApplicantNotFoundException("Заявку не знайдено");
//        specialtyForApplicantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
