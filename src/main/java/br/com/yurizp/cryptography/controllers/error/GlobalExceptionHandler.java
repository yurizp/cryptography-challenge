package br.com.yurizp.cryptography.controllers.error;

import br.com.yurizp.cryptography.domain.error.imp.BadRequestError;
import br.com.yurizp.cryptography.domain.error.imp.ConflictError;
import br.com.yurizp.cryptography.domain.error.Error;
import br.com.yurizp.cryptography.domain.error.ErrorBuilder;
import br.com.yurizp.cryptography.domain.error.imp.NotFoundError;
import br.com.yurizp.cryptography.domain.error.imp.SimpleError;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundError.class})
    public ResponseEntity<Object> handleStudentNotFoundException(NotFoundError exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getSimpleError());
    }

    @ExceptionHandler({BadRequestError.class})
    public ResponseEntity<Object> handleStudentNotFoundException(BadRequestError exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getSimpleError());
    }

    @ExceptionHandler({ConflictError.class})
    public ResponseEntity<Object> handleStudentNotFoundException(ConflictError exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getSimpleError());
    }

    @ExceptionHandler({Error.class})
    public ResponseEntity<Object> handleStudentNotFoundException(Error exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getSimpleError());
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(ValidationException exception) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Error error = ErrorBuilder.builder()
                .message(exception.getMessage())
                .code("bad.request")
                .build(BadRequestError.class);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error.getSimpleError());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(MethodArgumentNotValidException exception) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<SimpleError> simpleErrorList = Arrays.stream(exception.getDetailMessageArguments())
                .map(String::valueOf)
                .filter(StringUtils::isNotEmpty)
                .map(message -> new SimpleError("invalid.arguments", message))
                .toList();

        Error error = ErrorBuilder.builder()
                .message(exception.getMessage())
                .code("invalid.arguments")
                .details(simpleErrorList)
                .build(BadRequestError.class);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error.getSimpleError());
    }
}
