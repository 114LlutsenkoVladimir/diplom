package com.example.universityadmissionscommittee.dto;

public class BenefitIdAndName {

    private String name;

    private Long id;

    private String type;

    public BenefitIdAndName(String name, Long id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
