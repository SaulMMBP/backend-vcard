package io.github.saulmmbp.exceptions;

import java.io.Serial;

public class ResourceAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3696873975506477260L;

    public ResourceAlreadyExistsException() {
        super();
    }
    
    public ResourceAlreadyExistsException(String resource, Long resourceId) {
        super(resource + " with id " + resourceId + " already exists");
    }
    
    public ResourceAlreadyExistsException(String resource, String resourceId) {
        super(resource + " with id " + resourceId + " already exists");
    }
    
}
