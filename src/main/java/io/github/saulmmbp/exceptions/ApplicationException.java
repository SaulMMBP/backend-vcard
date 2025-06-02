package io.github.saulmmbp.exceptions;

import java.io.Serial;

public class ApplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3438260930864651865L;

    public ApplicationException(String message) {
        super(message);
    }

}
