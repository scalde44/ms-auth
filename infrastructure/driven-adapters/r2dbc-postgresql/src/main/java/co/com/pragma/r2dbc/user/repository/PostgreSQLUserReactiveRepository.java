package co.com.pragma.r2dbc.user.repository;

import co.com.pragma.r2dbc.user.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PostgreSQLUserReactiveRepository extends ReactiveCrudRepository<UserEntity, Long>, ReactiveQueryByExampleExecutor<UserEntity> {
    @Query("SELECT CASE WHEN COUNT(1) = 0 THEN true ELSE false END FROM public.usuario WHERE email = :email or documento_identidad = :documentNumber")
    Mono<Boolean> notExistsByEmailOrDocumentNumber(String email, String documentNumber);

    Mono<UserEntity> findByDocumentNumber(String documentNumber);

}
