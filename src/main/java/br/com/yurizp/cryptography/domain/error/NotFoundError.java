package br.com.yurizp.cryptography.domain.error;

import java.util.List;

public class NotFoundError extends Error {

    public NotFoundError(String errorMessage, String errorCode, Throwable cause, List<SimpleError> datails) {
        super(errorMessage, errorCode, cause, datails);
    }
}
