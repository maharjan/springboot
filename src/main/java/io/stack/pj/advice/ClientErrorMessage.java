package io.stack.pj.advice;

import lombok.Getter;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
public class ClientErrorMessage {

    private final String errorMessage;

    public ClientErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}