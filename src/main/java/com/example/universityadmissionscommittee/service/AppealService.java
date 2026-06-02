package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.Appeal;
import com.example.universityadmissionscommittee.repository.AppealRepository;
import com.example.universityadmissionscommittee.repository.SpecialtyForApplicantRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AppealService extends AbstractCrudService<Appeal, Long, AppealRepository> {

    private SpecialtyForApplicantRepository specialtyForApplicantRepository;

    protected AppealService(AppealRepository repository,
    SpecialtyForApplicantRepository specialtyForApplicantRepository) {
        super(repository);
        this.specialtyForApplicantRepository = specialtyForApplicantRepository;
    }

    public void create(@RequestParam Long specialtyForApplicantId,
                       @RequestParam String appealMessage) {

        repository.save(new Appeal(
                specialtyForApplicantRepository.findById(specialtyForApplicantId)
                        .orElseThrow(
                                () -> new RuntimeException("Заяву не знайдено")
                        ),
                appealMessage));
    }
}
