package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.Applicant;
import com.example.universityadmissionscommittee.exception.applicant.ApplicantNotFoundException;
import com.example.universityadmissionscommittee.exception.user.IncorrectPasswordException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private ApplicantService applicantService;
    private Long applicantId = -1L;

    public UserService(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    public String getUser(String password) {
        switch (password) {
            case "admin" -> {
                applicantId = -1L;
                return "admin";
            }
            case "committee" -> {
                applicantId = -1L;
                return "committee";
            }

            default -> {
                try {
                    Applicant applicant = applicantService.findByEmail(password);
                    applicantId = applicant.getId();
                    return "applicant";
                } catch (ApplicantNotFoundException e) {
                    throw new IncorrectPasswordException();
                }
            }

        }

    }

    public Long getIdIfApplicant() {
        return applicantId;
    }
}
