package com.example.universityadmissionscommittee.data.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QuotaType {
    QUOTA_1("Квота 1"),
    QUOTA_2("Квота 2"),
    NONE("Загальний конкурс");

    private String name;

    QuotaType(String name) {
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
