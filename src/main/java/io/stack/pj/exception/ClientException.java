package io.stack.pj.exception;

import lombok.Getter;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Getter
public class ClientException extends RuntimeException {

    public ClientException(String message) {
        super(message);
    }
}
