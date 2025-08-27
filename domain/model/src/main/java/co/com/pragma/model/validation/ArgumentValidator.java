package co.com.pragma.model.validation;

import co.com.pragma.model.exception.InvalidValueException;
import co.com.pragma.model.exception.MandatoryValueException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Pattern;

public class ArgumentValidator {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private ArgumentValidator() {
    }

    public static void validateRequired(Object value, String fieldName) {
        if (Objects.isNull(value)) {
            throw new MandatoryValueException(fieldName);
        }
    }

    public static void validateRequired(String value, String fieldName) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new MandatoryValueException(fieldName);
        }
    }

    public static void validateEmail(String email, String message) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidValueException(message);
        }
    }

    public static void validateRange(BigDecimal value, BigDecimal min, BigDecimal max, String message) {
        if (value.compareTo(min) < 0 || value.compareTo(max) > 0) {
            throw new InvalidValueException(message);
        }
    }
}
