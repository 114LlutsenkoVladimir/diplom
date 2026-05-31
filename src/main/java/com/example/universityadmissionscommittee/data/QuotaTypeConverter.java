package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.data.enums.QuotaType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class QuotaTypeConverter implements AttributeConverter<QuotaType, String> {
    @Override
    public String convertToDatabaseColumn(QuotaType type) {
        return type == null ? null : type.name();
    }

    @Override
    public QuotaType convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        try {
            return QuotaType.valueOf(dbValue);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Unknown value for QuotaType: " + dbValue, ex);
        }
    }
}
