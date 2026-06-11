package com.example.universityadmissionscommittee.data.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AppealStatus {
    APPROVED("Апеляція прийнята"),
    REJECTED("Апеляція відхилена"),
    PENDING("Невизначено");

    private String name;

    AppealStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() { return name(); }
    @Override
    public String toString() {
        return name;
    }
}
