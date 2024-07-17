package br.com.yurizp.cryptography.domain.error;

import br.com.yurizp.cryptography.domain.error.imp.BadRequestError;
import br.com.yurizp.cryptography.domain.error.imp.InternalServerError;
import br.com.yurizp.cryptography.domain.error.imp.SimpleError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ErrorBuilderTest {

    @Test
    void shouldReturnInternalServerErrorWhenClassHasMoreThanOnePublicConstructor() {
        Error error = ErrorBuilder.builder().build(ErrorWithTwoConstructor.class);

        assertAll(
                () -> assertNotNull(error.getSimpleError()),
                () -> assertNull(error.getCause()),
                () -> assertEquals(error.getSimpleError().code(), "internal.server.error"),
                () -> assertEquals(error.getSimpleError().message(), "Error to crate error class, more than one constructor"),
                () -> assertEquals(error.getClass(), InternalServerError.class)
        );
    }

    @Test
    void shouldReturnErrorBuilded() {
        RuntimeException cause = new RuntimeException("runtime exception");
        List<SimpleError> details = List.of(new SimpleError("simple.code", "simple.message"));
        String message = "test.message";
        String code = "test.code";

        Error errorResult = ErrorBuilder.builder()
                .code(code)
                .message(message)
                .details(details)
                .cause(cause)
                .build(BadRequestError.class);

        assertAll(
                () -> assertNotNull(errorResult.getSimpleError()),
                () -> assertEquals(errorResult.getCause(), cause),
                () -> assertEquals(errorResult.getSimpleError().code(), "test.code"),
                () -> assertEquals(errorResult.getSimpleError().message(), "test.message"),
                () -> assertEquals(errorResult.getSimpleError().datails(), details),
                () -> assertEquals(errorResult.getClass(), BadRequestError.class)
        );
    }

    private class ErrorWithTwoConstructor extends Error{

        public ErrorWithTwoConstructor(String errorMessage, String errorCode, Throwable cause, List<SimpleError> datails) {
            super(errorMessage, errorCode, cause, datails);
        }

        public ErrorWithTwoConstructor(String errorMessage, String errorCode) {
            super(errorMessage, errorCode, null, null);
        }
    }
}