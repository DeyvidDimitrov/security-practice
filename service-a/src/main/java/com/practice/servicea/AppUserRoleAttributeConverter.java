package com.practice.servicea;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AppUserRoleAttributeConverter implements AttributeConverter<Role, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Role attribute) {
        if (attribute == null)
            return null;

        switch (attribute) {
            case USER:
                return 1;

            case ADMIN:
                return 2;

            default:
                throw new InvalidEmailException(attribute + " not supported.");
        }
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case 1:
                return Role.USER;

            case 2:
                return Role.ADMIN;

            default:
                throw new InvalidEmailException(dbData + " not supported.");
        }
    }
}
