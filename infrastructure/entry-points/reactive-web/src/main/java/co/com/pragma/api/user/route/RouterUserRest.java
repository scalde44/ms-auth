package co.com.pragma.api.user.route;

import co.com.pragma.api.config.UserPath;
import co.com.pragma.api.user.dto.request.RegisterUserRequest;
import co.com.pragma.api.user.dto.response.RegisterUserResponse;
import co.com.pragma.api.user.dto.response.UserResponse;
import co.com.pragma.api.user.handler.UserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class RouterUserRest {
    private static final String TAG_API = "Usuarios";
    private final UserPath userPath;

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/usuarios",
                    produces = {"application/json"},
                    method = {org.springframework.web.bind.annotation.RequestMethod.POST},
                    beanClass = UserHandler.class,
                    beanMethod = "registerUser",
                    operation = @Operation(
                            tags = {TAG_API},
                            operationId = "registerUser",
                            summary = "Register a new user",
                            description = "Creates a new user and returns its id",
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(schema = @Schema(implementation = RegisterUserRequest.class))
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "201", description = "Created",
                                            content = @Content(schema = @Schema(implementation = RegisterUserResponse.class))),
                                    @ApiResponse(responseCode = "400", description = "Validation error"),
                                    @ApiResponse(responseCode = "409", description = "Email already exists")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/usuarios/{documento}",
                    produces = {"application/json"},
                    method = {org.springframework.web.bind.annotation.RequestMethod.GET},
                    beanClass = UserHandler.class,
                    beanMethod = "findUserByDocument",
                    operation = @Operation(
                            tags = {TAG_API},
                            operationId = "findUserByDocument",
                            summary = "Find user by document number",
                            parameters =

                                    {
                                            @Parameter(name = "documento", in = ParameterIn.PATH, required = true,
                                                    description = "User document number", example = "1012345678")
                                    },
                            responses =

                                    {
                                            @ApiResponse(responseCode = "200", description = "OK",
                                                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
                                            @ApiResponse(responseCode = "404", description = "Not found")
                                    }
                    )
            )
    })

    public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler) {
        return nest(path(this.userPath.getBase()),
                route(POST(""), userHandler::registerUser)
                        .andRoute(GET(this.userPath.getDocumentNumber()), userHandler::findUserByDocument));
    }
}
