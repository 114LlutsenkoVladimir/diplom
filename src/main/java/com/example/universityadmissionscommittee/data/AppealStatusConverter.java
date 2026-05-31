package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.data.enums.AppealStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AppealStatusConverter implements AttributeConverter<AppealStatus, String> {

    @Override
    public String convertToDatabaseColumn(AppealStatus status) {
        return status == null ? null : status.name();
    }

    @Override
    public AppealStatus convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        try {
            return AppealStatus.valueOf(dbValue);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Unknown value for AppealStatus: " + dbValue, ex);
        }
    }

}
