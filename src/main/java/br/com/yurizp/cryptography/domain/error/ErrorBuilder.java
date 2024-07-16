package br.com.yurizp.cryptography.domain.error;

import br.com.yurizp.cryptography.domain.error.imp.InternalServerError;
import br.com.yurizp.cryptography.domain.error.imp.SimpleError;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ErrorBuilder {

    private List<SimpleError> details;
    private String code;
    private Throwable cause;
    private String message;

    private ErrorBuilder() {
    }

    public static ErrorBuilder builder() {
        return new ErrorBuilder();
    }

    public ErrorBuilder details(List<SimpleError> details) {
        this.details = details;
        return this;
    }

    public ErrorBuilder code(String code) {
        this.code = code;
        return this;
    }

    public ErrorBuilder cause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    public ErrorBuilder message(String message) {
        this.message = message;
        return this;
    }

    public <T extends Error> Error build(Class<T> errorClass) {
        try {
            Constructor<?>[] declaredConstructors = errorClass.getDeclaredConstructors();
            if (declaredConstructors.length != 1) {
                log.error("Class %s has more than one constructor then not possible create in ErrorBuilder".formatted(errorClass.getName()));
                return new InternalServerError("Error to crate error class, more than one constructor", "internal.server.error", null, null);
            }
            return (Error) Arrays.stream(declaredConstructors).findFirst().get().newInstance(message, code, cause, details);
        } catch (Exception e) {
            log.error("Error to create class %s in ErrorBuilder".formatted(errorClass.getName()), e);
            return new InternalServerError("Error to crate error class", "internal.server.error", e.getCause(), null);
        }
    }
}
