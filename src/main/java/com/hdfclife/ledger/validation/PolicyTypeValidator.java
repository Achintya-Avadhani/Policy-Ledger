package com.hdfclife.ledger.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class PolicyTypeValidator implements ConstraintValidator<ValidPolicyType, String> {

    private static final Set<String> ALLOWED = Arrays.stream(PolicyType.values())
            .map(Enum::name)
            .collect(Collectors.toUnmodifiableSet());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return ALLOWED.contains(value.trim().toUpperCase(Locale.ROOT));
    }
}
