package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.Appeal;
import com.example.universityadmissionscommittee.dto.appeal.AppealReportGrouped;
import com.example.universityadmissionscommittee.repository.AppealRepository;
import com.example.universityadmissionscommittee.repository.SpecialtyForApplicantRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppealService extends AbstractCrudService<Appeal, Long, AppealRepository> {

    private SpecialtyForApplicantRepository specialtyForApplicantRepository;

    protected AppealService(AppealRepository repository,
    SpecialtyForApplicantRepository specialtyForApplicantRepository) {
        super(repository);
        this.specialtyForApplicantRepository = specialtyForApplicantRepository;
    }

    public void create(Long specialtyForApplicantId,
                       String appealMessage) {

        repository.save(new Appeal(
                specialtyForApplicantRepository.findById(specialtyForApplicantId)
                        .orElseThrow(
                                () -> new RuntimeException("Заяву не знайдено")
                        ),
                appealMessage));
    }

    public AppealReportGrouped appealsBySpecialties(List<Long> specialtyIds) {
        return new AppealReportGrouped(repository.appealsBySpecialties(specialtyIds));
    }

    public AppealReportGrouped appealsByOneSpecialty(Long specialtyId) {
        return appealsBySpecialties(new ArrayList<>(List.of(specialtyId)));
    }
}
