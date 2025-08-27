package co.com.pragma.api.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "RegisterUserRequest")
public record RegisterUserRequest(
        @Schema(example = "Steven", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,
        @Schema(example = "Calderon", requiredMode = Schema.RequiredMode.REQUIRED)
        String lastName,
        @Schema(example = "1005872803", requiredMode = Schema.RequiredMode.REQUIRED)
        String documentNumber,
        @Schema(example = "3017976885", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String phoneNumber,
        @Schema(example = "scalderon1504@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,
        @Schema(example = "10000000", requiredMode = Schema.RequiredMode.REQUIRED,
                type = "string")
        BigDecimal baseSalary,
        @Schema(example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
        Long roleId) {
}
