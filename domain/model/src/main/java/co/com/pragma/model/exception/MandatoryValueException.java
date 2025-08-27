package co.com.pragma.model.exception;

public class MandatoryValueException extends RuntimeException {
    private static final String REQUIRED_FIELD = "The field %s is required";

    public MandatoryValueException(String fieldName) {
        super(String.format(REQUIRED_FIELD, fieldName));
    }
}
