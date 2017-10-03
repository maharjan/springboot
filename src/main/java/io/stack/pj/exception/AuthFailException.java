package io.stack.pj.exception;

import lombok.Getter;

@Getter
public class AuthFailException extends RuntimeException {

    public AuthFailException(String message) {
        super(message);
    }
}
