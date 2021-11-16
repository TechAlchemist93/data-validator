package com.rabbi.data.validator;

import com.rabbi.data.validator.exception.ResourceAlreadyExistsException;
import com.rabbi.data.validator.exception.ResourceNotFoundException;
import org.springframework.core.GenericTypeResolver;

import java.util.Optional;

interface DataValidatorBase<T, K> {

     default String getDataClassName() {
        Class<?> dataClass = GenericTypeResolver.resolveTypeArgument(getClass(), DataValidatorBase.class);
        return dataClass != null ? dataClass.getSimpleName() : "Object";
    }

    K noError(T data);

    K handleNotFound(T data, ResourceNotFoundException ex);

    K handleAlreadyExists(T data, ResourceAlreadyExistsException ex);

    default K validate(T data, String identifier, String value, FailCase failCase) {
        if (failCase != null) {
            if (FailCase.WHEN_EXISTS.equals(failCase)) {
                return handleAlreadyExists(data, new ResourceAlreadyExistsException(
                    String.format("%s with %s %s already exists", getDataClassName(), identifier, value)));
            } else if (FailCase.WHEN_NOT_EXISTS.equals(failCase)) {
                return handleNotFound(data, new ResourceNotFoundException(
                    String.format("No %s found for %s: %s", getDataClassName().toLowerCase(), identifier, value)));
            }
        }

        return noError(data);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    default K validate(Optional<T> dataOp, String identifier, String value, FailCase failCase) {
        if (failCase != null) {
            if (FailCase.WHEN_EXISTS.equals(failCase)) {
                return dataOp.map(data -> handleAlreadyExists(data, new ResourceAlreadyExistsException(
                        String.format("%s with %s %s already exists", getDataClassName(), identifier, value))))
                        .orElse(null);
            } else if (FailCase.WHEN_NOT_EXISTS.equals(failCase) && dataOp.isEmpty()) {
                return handleNotFound(null, new ResourceNotFoundException(
                        String.format("No %s found for %s: %s", getDataClassName().toLowerCase(), identifier, value)));
            }
        }

        return dataOp.map(this::noError).orElse(null);
    }
}
