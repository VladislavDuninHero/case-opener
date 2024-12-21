package com.case_opener_game.case_opener.validation.validator;

import com.case_opener_game.case_opener.validation.annotations.EnumValidate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValidate, String> {

    private Enum<?>[] enums;

    @Override
    public void initialize(EnumValidate constraintAnnotation) {
         enums = constraintAnnotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        for (Enum<?> enumConstant : enums) {
            if (enumConstant.name().equals(value.toUpperCase())) {
                return true;
            }
        }

        return false;
    }

}
