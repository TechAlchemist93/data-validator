package com.rabbi.data.validator;

import com.rabbi.data.validator.exception.ResourceAlreadyExistsException;
import com.rabbi.data.validator.exception.ResourceNotFoundException;
import reactor.core.publisher.Mono;

public interface ReactiveDataValidator<T> extends DataValidatorBase<Mono<T>, Mono<T>> {

    @Override
    default Mono<T> noError(Mono<T> data) {
        return data;
    }

    @Override
    default Mono<T> handleNotFound(Mono<T> data, ResourceNotFoundException ex) {
        return data.switchIfEmpty(Mono.error(ex));
    }

    @Override
    default Mono<T> handleAlreadyExists(Mono<T> data, ResourceAlreadyExistsException ex) {
        return data.flatMap(obj -> Mono.error(ex));
    }
}
