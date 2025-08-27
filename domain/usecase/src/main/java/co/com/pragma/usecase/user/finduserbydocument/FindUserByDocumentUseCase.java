package co.com.pragma.usecase.user.finduserbydocument;

import co.com.pragma.model.exception.NotFoundException;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindUserByDocumentUseCase {
    private static final String USER_NOT_FOUND = "User with document '%s' doesn't exist";
    private final UserRepository userRepository;

    public Mono<User> execute(String documentNumber) {
        return this.userRepository.findByDocumentNumber(documentNumber)
                .switchIfEmpty(Mono.error(new NotFoundException(String.format(USER_NOT_FOUND, documentNumber))));
    }
}
