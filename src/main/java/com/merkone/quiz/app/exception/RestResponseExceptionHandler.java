package com.merkone.quiz.app.exception;

import com.merkone.api.quiz.model.QuizExceptionDTO;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author vescudero
 */
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseExceptionHandler() {
        super();
    }

    // 400
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgument(final Exception ex, final WebRequest request) {
        return handleException(ex, null, HttpStatus.BAD_REQUEST, request);
    }

    // 403
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDenied(final Exception ex, final WebRequest request) {
        return handleException(ex, null, HttpStatus.FORBIDDEN, request);
    }

    // 404
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(final NotFoundException ex, final WebRequest request) {
        QuizExceptionDTO error = new QuizExceptionDTO();
        error.setCode(BigDecimal.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setMessage(String.format("La entidad %s no existe", ex.getMessage()));
        return handleException(ex, error, HttpStatus.NOT_FOUND, request);
    }

    // 500
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternal(final Exception ex, final WebRequest request) {
        QuizExceptionDTO error = new QuizExceptionDTO();
        error.setCode(BigDecimal.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        // TODO: Check the exception to identify the error and the affected field
        error.setMessage("Se ha producido un error procesando la petición");
        error.setField("-");
        return handleException(ex, error, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> handleException(Exception ex, Object body, HttpStatus httpStatus, WebRequest request) {
        this.logger.error("Exception: " + ex.getMessage() + " --> " + httpStatus.getReasonPhrase(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), httpStatus, request);
    }

}
