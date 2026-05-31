package com.example.universityadmissionscommittee.dto.specialty;

public class SpecialtyReportDto {
    private Long id;
    private Long facultyId;
    private String name;
    private Integer number;
    private String  facultyName;
    private Integer budgetPlacesQuota1;
    private Integer budgetPlacesQuota2;
    private Integer budgetPlacesGeneral;
    private Integer numberOfContractPlaces;
    private Integer sumOfPlaces;

    public SpecialtyReportDto(Long id, String name,
                              Integer number,
                              Long facultyId,
                              String facultyName,
                              Integer budgetPlacesQuota1,
                              Integer budgetPlacesQuota2,
                              Integer budgetPlacesGeneral,
                              Integer numberOfContractPlaces,
                              Integer sumOfPlaces) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.budgetPlacesQuota1 = budgetPlacesQuota1;
        this.budgetPlacesQuota2 = budgetPlacesQuota2;
        this.budgetPlacesGeneral = budgetPlacesGeneral;
        this.numberOfContractPlaces = numberOfContractPlaces;
        this.sumOfPlaces = sumOfPlaces;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Long getFacultyId() {return facultyId;}
    public String getFacultyName() {
        return facultyName;
    }

    public Integer getBudgetPlacesQuota1() {
        return budgetPlacesQuota1;
    }

    public Integer getBudgetPlacesQuota2() {
        return budgetPlacesQuota2;
    }

    public Integer getBudgetPlacesGeneral() {
        return budgetPlacesGeneral;
    }

    public Integer getNumberOfContractPlaces() {
        return numberOfContractPlaces;
    }

    public Integer getSumOfPlaces() {
        return sumOfPlaces;
    }
}
