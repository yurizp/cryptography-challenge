package br.com.yurizp.cryptography.domain.error;

import java.util.List;

public abstract class Error extends Throwable {

    private final SimpleError simpleError;

   public Error(String errorMessage, String errorCode, Throwable cause, List<SimpleError> datails) {
        super(errorMessage, cause);
        this.simpleError = new SimpleError(errorCode, errorMessage, datails);
    }

    public SimpleError getSimpleError() {
        return simpleError;
    }

}
