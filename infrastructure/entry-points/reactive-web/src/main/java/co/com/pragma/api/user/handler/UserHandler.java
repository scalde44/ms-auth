package co.com.pragma.api.user.handler;

import co.com.pragma.api.config.UserPath;
import co.com.pragma.api.user.dto.request.RegisterUserRequest;
import co.com.pragma.api.user.dto.response.RegisterUserResponse;
import co.com.pragma.api.user.dto.response.UserResponse;
import co.com.pragma.api.user.helper.UserMapper;
import co.com.pragma.usecase.user.finduserbydocument.FindUserByDocumentUseCase;
import co.com.pragma.usecase.user.registeruser.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {
    private final UserMapper userMapper;
    private final UserPath userPath;
    private final RegisterUserUseCase registerUserUseCase;
    private final FindUserByDocumentUseCase findUserByDocumentUseCase;

    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(RegisterUserRequest.class)
                .map(this.userMapper::map)
                .flatMap(this.registerUserUseCase::execute)
                .flatMap(id -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(new RegisterUserResponse(id))
                );
    }

    public Mono<ServerResponse> findUserByDocument(ServerRequest serverRequest) {
        final String documentNumber = serverRequest.pathVariable(this.userPath.getDocumentNumberVar());
        return this.findUserByDocumentUseCase.execute(documentNumber)
                .flatMap(user -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(new UserResponse(user.getId(), user.getEmail()))
                );
    }
}
