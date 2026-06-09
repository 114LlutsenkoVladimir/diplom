package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.Applicant;
import com.example.universityadmissionscommittee.data.SpecialtyForApplicant;
import com.example.universityadmissionscommittee.exception.applicant.ApplicantNotFoundException;
import com.example.universityadmissionscommittee.repository.SpecialtyForApplicantRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyForApplicantService extends AbstractCrudService<SpecialtyForApplicant,
        Long, SpecialtyForApplicantRepository>{

    public SpecialtyForApplicantService(SpecialtyForApplicantRepository repository) {
        super(repository);
    }

    @Override
    public SpecialtyForApplicant findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ApplicantNotFoundException("Заявку не знайдено")
        );
    }

}
