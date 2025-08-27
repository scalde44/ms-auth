package co.com.pragma.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static co.com.pragma.model.validation.ArgumentValidator.validateEmail;
import static co.com.pragma.model.validation.ArgumentValidator.validateRange;
import static co.com.pragma.model.validation.ArgumentValidator.validateRequired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private static final String INVALID_EMAIL_FORMAT = "Invalid email format";
    private static final String INVALID_BASE_SALARY = "The base salary must be between %s and %s";
    private static final BigDecimal MIN_SALARY = BigDecimal.ZERO;
    private static final BigDecimal MAX_SALARY = new BigDecimal("15000000");
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String documentNumber;
    private String phoneNumber;
    private BigDecimal baseSalary;
    private Long roleId;

    public User(String name, String lastName, String documentNumber, String phoneNumber, String email, BigDecimal baseSalary, Long roleId) {
        validateRequired(name, "name");
        validateRequired(lastName, "lastName");
        validateRequired(documentNumber, "documentNumber");
        validateRequired(email, "email");
        validateEmail(email, INVALID_EMAIL_FORMAT);
        validateRequired(baseSalary, "baseSalary");
        validateRange(baseSalary, MIN_SALARY, MAX_SALARY, String.format(INVALID_BASE_SALARY, MIN_SALARY, MAX_SALARY));
        validateRequired(roleId, "roleId");
        this.name = name;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.baseSalary = baseSalary;
        this.roleId = roleId;
    }
}
