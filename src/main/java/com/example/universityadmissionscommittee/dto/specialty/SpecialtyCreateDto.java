package com.example.universityadmissionscommittee.dto.specialty;

import java.util.HashMap;

public class SpecialtyCreateDto {

    private String name;

    private Integer number;

    private Long facultyId;

    private Integer budgetPlaces = 0;

    private Integer budgetPlacesQuota1 = 0;

    private Integer budgetPlacesQuota2 = 0;

    private Integer contractPlaces = 0;

    private HashMap<Long, Double> subjects = new HashMap<>();

    protected SpecialtyCreateDto() {
    }

    public void addSubject(Long id, Double weight) {
        subjects.put(id, weight);
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public Integer getBudgetPlaces() {
        return budgetPlaces;
    }

    public Integer getContractPlaces() {
        return contractPlaces;
    }

    public HashMap<Long, Double> getSubjects() {
        return subjects;
    }

    public Integer getBudgetPlacesQuota1() {
        return budgetPlacesQuota1;
    }

    public Integer getBudgetPlacesQuota2() {
        return budgetPlacesQuota2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public void setBudgetPlaces(Integer budgetPlaces) {
        this.budgetPlaces = budgetPlaces;
    }

    public void setContractPlaces(Integer contractPlaces) {
        this.contractPlaces = contractPlaces;
    }

    public void setSubjects(HashMap<Long, Double> subjects) {
        this.subjects = subjects;
    }

    public void setBudgetPlacesQuota1(Integer budgetPlacesQuota1) {
        this.budgetPlacesQuota1 = budgetPlacesQuota1;
    }

    public void setBudgetPlacesQuota2(Integer budgetPlacesQuota2) {
        this.budgetPlacesQuota2 = budgetPlacesQuota2;
    }
}
