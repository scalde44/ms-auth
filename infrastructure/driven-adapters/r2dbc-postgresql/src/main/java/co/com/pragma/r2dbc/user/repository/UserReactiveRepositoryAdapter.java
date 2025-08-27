package co.com.pragma.r2dbc.user.repository;

import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import co.com.pragma.r2dbc.user.entity.UserEntity;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        User,
        UserEntity,
        Long,
        PostgreSQLUserReactiveRepository
        >
        implements UserRepository {
    public UserReactiveRepositoryAdapter(PostgreSQLUserReactiveRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, u -> mapper.map(u, User.class));
    }

    @Override
    public Mono<Boolean> notExistsByEmailOrDocumentNumber(String email, String documentNumber) {
        return repository.notExistsByEmailOrDocumentNumber(email, documentNumber);
    }

    @Override
    public Mono<User> findByDocumentNumber(String documentNumber) {
        return repository.findByDocumentNumber(documentNumber)
                .map(this::toEntity);
    }
}
