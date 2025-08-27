package co.com.pragma.api.error;

import co.com.pragma.model.exception.DuplicateException;
import co.com.pragma.model.exception.InvalidValueException;
import co.com.pragma.model.exception.MandatoryValueException;
import co.com.pragma.model.exception.NotFoundException;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler
        extends AbstractErrorWebExceptionHandler {
    private static final String
            AN_ERROR_OCCURRED_PLEASE_CONTACT_THE_ADMINISTRATOR = "An error occurred, please contact the administrator";
    private static final ConcurrentHashMap<String, Integer> STATUS_CODES = new ConcurrentHashMap<>();

    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources,
                                          ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
        super(errorAttributes, resources, applicationContext);
        this.setMessageWriters(configurer.getWriters());
        STATUS_CODES.put(DuplicateException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATUS_CODES.put(InvalidValueException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATUS_CODES.put(MandatoryValueException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATUS_CODES.put(NotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(
            ErrorAttributes errorAttributes) {
        return RouterFunctions.route(
                RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(
            ServerRequest request) {
        Throwable error = getError(request);
        String nameException = error.getClass().getSimpleName();
        Optional<Integer> codeStatus = Optional.ofNullable(STATUS_CODES.get(nameException));
        return ServerResponse
                .status(codeStatus.orElse(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(codeStatus
                        .map(code -> this.createProblemDetail(nameException, error.getMessage(), code))
                        .orElse(
                                this.createProblemDetail(nameException, AN_ERROR_OCCURRED_PLEASE_CONTACT_THE_ADMINISTRATOR,
                                        HttpStatus.INTERNAL_SERVER_ERROR.value())
                        )
                );
    }

    private ProblemDetail createProblemDetail(String title, String detail, int status) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(status), detail);
        problemDetail.setTitle(title);
        return problemDetail;
    }

}