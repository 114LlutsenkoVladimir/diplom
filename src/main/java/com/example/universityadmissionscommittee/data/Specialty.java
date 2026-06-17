package com.example.universityadmissionscommittee.data;


import com.example.universityadmissionscommittee.exception.specialty.SpecialtyCreationException;
import com.example.universityadmissionscommittee.exception.specialty.SpecialtyNotFoundException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.*;

@Entity
@Table(
        name = "specialty",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_specialty_name", columnNames = "name"),
                @UniqueConstraint(name = "uq_specialty_number", columnNames = "number")
        }
)
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "number", nullable = false)
    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Column(name = "budget_places_quota1", nullable = false)
    private int budgetPlacesQuota1;

    @Column(name = "budget_places_quota2", nullable = false)
    private int budgetPlacesQuota2;

    @Column(name = "budget_places_general", nullable = false)
    private int budgetPlacesGeneral;

    @Column(name = "number_of_contract_places", nullable = false)
    private int numberOfContractPlaces;

    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubjectForSpecialty> subjectWeights = new HashSet<>();

    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<SpecialtyForApplicant> applicants = new HashSet<>();

    protected Specialty() {
    }

    public Specialty(String name, int number, Faculty faculty,
                     int budgetPlacesGeneral, int budgetPlacesQuota1,
                     int budgetPlacesQuota2, int numberOfContractPlaces) {
        this.name = Objects.requireNonNull(name, "name");
        this.number = number;
        this.faculty = Objects.requireNonNull(faculty, "faculty");
        this.budgetPlacesGeneral = budgetPlacesGeneral;
        this.numberOfContractPlaces = numberOfContractPlaces;
        this.budgetPlacesQuota1 = budgetPlacesQuota1;
        this.budgetPlacesQuota2 = budgetPlacesQuota2;
    }



    public void addSubject(Subject subject, Double weight) {
        SubjectForSpecialty sfs = new SubjectForSpecialty(weight, this, subject);
        subjectWeights.add(sfs);
        subject.getSpecialtyWeights().add(sfs);
    }

    public void removeSubject(Subject subject) {
        for (Iterator<SubjectForSpecialty> iterator = subjectWeights.iterator(); iterator.hasNext(); ) {
            SubjectForSpecialty sfs = iterator.next();
            if (sfs.getSpecialty().equals(this) && sfs.getSubject().equals(subject)) {
                iterator.remove();
                sfs.getSubject().getSpecialtyWeights().remove(sfs);
                sfs.setSpecialty(null);
                sfs.setSubject(null);
                break;
            }
        }
    }

    public boolean hasNoApplicants() {
        return applicants.isEmpty();
    }



    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) {
        this.faculty = Objects.requireNonNull(faculty, "faculty");
    }

    public int getBudgetPlacesGeneral() { return budgetPlacesGeneral; }
    public void setBudgetPlacesGeneral(int numberOfBudgetPlaces) {
        this.budgetPlacesGeneral = numberOfBudgetPlaces;
    }

    public int getNumberOfContractPlaces() { return numberOfContractPlaces; }
    public void setNumberOfContractPlaces(int numberOfContractPlaces) {
        this.numberOfContractPlaces = numberOfContractPlaces;
    }

    public Set<SubjectForSpecialty> getSubjectWeights() {
        return subjectWeights;
    }

    public Set<SpecialtyForApplicant> getApplicants() {
        return Collections.unmodifiableSet(applicants);
    }

    public int getBudgetPlacesQuota1() {
        return budgetPlacesQuota1;
    }

    public int getBudgetPlacesQuota2() {
        return budgetPlacesQuota2;
    }

    public void setBudgetPlacesQuota2(int budgetPlacesQuota2) {
        this.budgetPlacesQuota2 = budgetPlacesQuota2;
    }

    public void setBudgetPlacesQuota1(int budgetPlacesQuota1) {
        this.budgetPlacesQuota1 = budgetPlacesQuota1;
    }

    public void increaseBudgetPlacesGeneral() {
        budgetPlacesGeneral++;
    }

    public void decreaseBudgetPlacesGeneral() {
        if(budgetPlacesGeneral - 1 < 0)
            throw new SpecialtyCreationException("Бюджетних місць загального конкурсу не залишилось");
        budgetPlacesGeneral--;
    }

    public void increaseBudgetPlacesQuota1() {
        budgetPlacesQuota1++;
    }

    public void decreaseBudgetPlacesQuota1() {
        if(budgetPlacesQuota1 - 1 < 0)
            throw new SpecialtyCreationException("Бюджетних місць квоти 1 не залишилось");
        budgetPlacesQuota1--;
    }

    public void increaseBudgetPlacesQuota2() {
        budgetPlacesQuota2++;
    }

    public void decreaseBudgetPlacesQuota2() {
        if(budgetPlacesQuota2 - 1 < 0)
            throw new SpecialtyCreationException("Бюджетних місць квоти 2 не залишилось");
        budgetPlacesQuota2--;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Specialty other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() { return getClass().hashCode(); }

    @Override
    public String toString() {
        return "Specialty{id=" + id + ", name='" + name + "', number=" + number + "}";
    }
}

