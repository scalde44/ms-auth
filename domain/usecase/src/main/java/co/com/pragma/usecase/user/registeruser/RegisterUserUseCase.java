package co.com.pragma.usecase.user.registeruser;

import co.com.pragma.model.exception.DuplicateException;
import co.com.pragma.model.exception.NotFoundException;
import co.com.pragma.model.role.gateways.RoleRepository;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RegisterUserUseCase {
    private static final String USER_ALREADY_EXISTS = "User already exists";
    private static final String ROLE_NOT_FOUND = "Role with id %d doesn't exist";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Mono<Long> execute(User user) {
        return Mono.just(user)
                .filterWhen(u -> this.userRepository.notExistsByEmailOrDocumentNumber(u.getEmail(), u.getDocumentNumber()))
                .switchIfEmpty(Mono.error(new DuplicateException(USER_ALREADY_EXISTS)))
                .filterWhen(u -> this.roleRepository.existsById(u.getRoleId()))
                .switchIfEmpty(Mono.error(new NotFoundException(String.format(ROLE_NOT_FOUND, user.getRoleId()))))
                .flatMap(this.userRepository::save)
                .map(User::getId);
    }
}
