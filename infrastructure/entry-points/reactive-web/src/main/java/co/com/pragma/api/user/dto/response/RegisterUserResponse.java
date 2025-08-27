package co.com.pragma.api.user.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "RegisterUserResponse")
public record RegisterUserResponse(
        @Schema(example = "1")
        Long id
) {
}
