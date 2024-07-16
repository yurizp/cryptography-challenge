package br.com.yurizp.cryptography.domain.error;

import java.util.List;

public class ConflictError extends Error {

    public ConflictError(String errorMessage, String errorCode, Throwable cause, List<SimpleError> datails) {
        super(errorMessage, errorCode, cause, datails);
    }
}
