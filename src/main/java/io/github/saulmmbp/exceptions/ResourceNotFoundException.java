package io.github.saulmmbp.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -7257556391565153531L;
	
	public ResourceNotFoundException() {
		super();
	}
	
	public ResourceNotFoundException(String resourceName, Long resourceId) {
		super(resourceName + " with id " + resourceId + " not found");
	}

}
