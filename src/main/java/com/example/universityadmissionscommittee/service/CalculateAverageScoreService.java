package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.dto.BenefitIdNamePoints;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantReportDto;

import java.util.Map;

public class CalculateAverageScoreService {
    public static double calculate(ApplicantReportDto applicant,
                                   Map<Long, Double> subjectWeights) {
        double res = 0;
        for (Map.Entry<Long, Integer> entry : applicant.getSubjectAndScore().entrySet()) {
            Long subjectId = entry.getKey();
            Integer score = entry.getValue();

            Double weight = subjectWeights.getOrDefault(subjectId, 0.0);

            res += (score * weight);
        }

        return res;
    }
}
