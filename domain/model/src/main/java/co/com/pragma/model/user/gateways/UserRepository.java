package co.com.pragma.model.user.gateways;

import co.com.pragma.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> save(User user);

    Mono<Boolean> notExistsByEmailOrDocumentNumber(String email, String documentNumber);

    Mono<User> findByDocumentNumber(String documentNumber);
}
