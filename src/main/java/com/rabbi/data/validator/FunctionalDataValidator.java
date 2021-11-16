package com.rabbi.data.validator;

import com.rabbi.data.validator.exception.ResourceAlreadyExistsException;
import com.rabbi.data.validator.exception.ResourceNotFoundException;
import io.vavr.control.Try;

public interface FunctionalDataValidator<T> extends DataValidatorBase<T, Try<T>> {

    @Override
    default Try<T> noError(T data) {
        return Try.success(data);
    }

    @Override
    default Try<T> handleNotFound(T data, ResourceNotFoundException ex) {
        if (data == null) {
            return Try.failure(ex);
        }

        return noError(data);
    }

    @Override
    default Try<T> handleAlreadyExists(T data, ResourceAlreadyExistsException ex) {
        if (data != null) {
            return Try.failure(ex);
        }

        return noError(data);
    }
}
