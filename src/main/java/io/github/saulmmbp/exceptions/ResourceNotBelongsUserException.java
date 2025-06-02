package io.github.saulmmbp.exceptions;

import java.io.Serial;

public class ResourceNotBelongsUserException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8666670378406989790L;

    public ResourceNotBelongsUserException() {
        super();
    }

    public ResourceNotBelongsUserException(String resourceName, Long resourceId, String userId) {
        super(resourceName + " with id " + resourceId + " not belongs to user with id " + userId);
    }

}
