package com.example.universityadmissionscommittee.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subject_for_specialty")
public class SubjectForSpecialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Column(name = "weight")
    private Double weight;

    public SubjectForSpecialty(Double weight,
                               Specialty specialty,
                               Subject subject) {
        this.weight = weight;
        this.specialty = specialty;
        this.subject = subject;
    }

    protected SubjectForSpecialty() {
    }
}