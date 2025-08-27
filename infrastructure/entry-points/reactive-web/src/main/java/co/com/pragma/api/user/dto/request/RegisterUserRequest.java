package co.com.pragma.api.user.dto.request;

import java.math.BigDecimal;

public record RegisterUserRequest(String name, String lastName, String documentNumber, String phoneNumber,
                                  String email, BigDecimal baseSalary, Long roleId) {
}
