package com.rabbi.data.validator;

import com.rabbi.data.validator.exception.ResourceAlreadyExistsException;
import com.rabbi.data.validator.exception.ResourceNotFoundException;

public interface SimpleDataValidator<T> extends DataValidatorBase<T, T> {

    @Override
    default T noError(T data) {
        return data;
    }

    @Override
    default T handleNotFound(T data, ResourceNotFoundException ex) {
        if (data == null) {
            throw ex;
        }

        return data;
    }

    @Override
    default T handleAlreadyExists(T data, ResourceAlreadyExistsException ex) {
        if (data != null) {
            throw ex;
        }

        return data;
    }
}
