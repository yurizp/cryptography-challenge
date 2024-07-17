package br.com.yurizp.cryptography.domain.error.imp;

import br.com.yurizp.cryptography.domain.error.Error;

import java.util.List;

public class BadRequestError extends Error {

    public BadRequestError(String errorMessage, String errorCode, Throwable cause, List<SimpleError> simpleErrorList) {
        super(errorMessage, errorCode, cause, simpleErrorList);
    }

}
