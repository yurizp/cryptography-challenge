package br.com.yurizp.cryptography.domain.error;

import java.util.List;

public class BadRequestError extends Error {

    public BadRequestError(String errorMessage, String errorCode, Throwable cause, List<SimpleError> simpleErrorList) {
        super(errorMessage, errorCode, cause, simpleErrorList);
    }

}
