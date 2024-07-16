package br.com.yurizp.cryptography.domain.error.imp;

import br.com.yurizp.cryptography.domain.error.Error;

import java.util.List;

public class InternalServerError extends Error {
    public InternalServerError(String errorMessage, String errorCode, Throwable cause, List<SimpleError> datails) {
        super(errorMessage, errorCode, cause, datails);
    }
}
