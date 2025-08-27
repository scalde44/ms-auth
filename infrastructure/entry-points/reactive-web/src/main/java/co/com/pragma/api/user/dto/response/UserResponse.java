package co.com.pragma.api.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserResponse")
public record UserResponse(
        @Schema(example = "1")
        Long id,
        @Schema(example = "scalderon1504@gmail.com")
        String email
) {
}
