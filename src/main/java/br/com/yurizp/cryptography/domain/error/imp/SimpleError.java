package br.com.yurizp.cryptography.domain.error.imp;

import java.util.List;

public record SimpleError(String code, String message, List<SimpleError> datails) {
    public SimpleError {
    }

    public SimpleError(String code, String message) {
        this(code, message, null);
    }

}
