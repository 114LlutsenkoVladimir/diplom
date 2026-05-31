package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.data.enums.AppealStatus;
import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "appeal")
public class Appeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "specialty_for_applicant_id", nullable = false)
    private SpecialtyForApplicant specialtyForApplicant;

    @Size(max = 1000)
    @NotNull
    @Column(name = "message", nullable = false, length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "appeal_status", nullable = false, length = 50)
    private AppealStatus appealStatus = AppealStatus.PENDING;
}