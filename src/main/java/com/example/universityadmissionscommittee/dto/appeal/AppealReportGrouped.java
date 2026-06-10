package com.example.universityadmissionscommittee.dto.appeal;

import com.example.universityadmissionscommittee.exception.specialty.SpecialtyNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppealReportGrouped {
    Map<Long, String> specialtyNames = new HashMap<>();

    Map<Long, List<AppealReportDto>> report = new HashMap<>();

    public AppealReportGrouped(List<AppealReportDto> dtos) {
        if (dtos == null || dtos.isEmpty())
            throw new SpecialtyNotFoundException();

        buildReport(dtos);
    }

    private void buildReport(List<AppealReportDto> dtos) {

        for (AppealReportDto dto : dtos) {
            specialtyNames.putIfAbsent(dto.specialtyId(), dto.specialtyName());
        }

        report = dtos.stream().collect(Collectors.groupingBy(AppealReportDto::specialtyId));
    }

    public Map<Long, String> getSpecialtyNamesNames() {
        return specialtyNames;
    }

    public Map<Long, List<AppealReportDto>> getReport() {
        return report;
    }
}
