package co.com.pragma.api.user.route;

import co.com.pragma.api.config.UserPath;
import co.com.pragma.api.user.handler.UserHandler;
import lombok.RequiredArgsConstructor;
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
    private final UserPath userPath;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler) {
        return nest(path(this.userPath.getBase()),
                route(POST(""), userHandler::registerUser)
                        .andRoute(GET(this.userPath.getDocumentNumber()), userHandler::findUserByDocument));
    }
}
