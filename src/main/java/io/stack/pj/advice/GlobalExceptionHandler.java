package io.stack.pj.advice;

import io.stack.pj.exception.AuthFailException;
import io.stack.pj.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *     All exceptions(declared) thrown in inner beans and bubbled up to controller is handled here.
 *    This class wrap the error message along with proper HTTP Status code as error response to client.
 * </p>
 *
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(AuthFailException.class)
//    public String authFailException(AuthFailException exe) {
//        log.error(exe.getMessage());
//        return "login";
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ClientErrorMessage> illegalException(IllegalArgumentException exe) {
        log.error(exe.getMessage());
        return new ResponseEntity<>(new ClientErrorMessage(exe.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ClientErrorMessage> clientError(ClientException exe) {
        log.error(exe.getMessage());
        return new ResponseEntity<>(new ClientErrorMessage(exe.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ClientErrorMessage> noResultFound(EmptyResultDataAccessException exe) {
        log.error(exe.getMessage());
        return new ResponseEntity<>(new ClientErrorMessage(exe.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ClientErrorMessage> generalError(Exception exe) {
        log.error(exe.getMessage());
        return new ResponseEntity<>(new ClientErrorMessage(exe.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}