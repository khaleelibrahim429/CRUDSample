package com.sos.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
//@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2192819893732070509L;

	private String resourceName;
	private String resourceId;
	private Object resourceValue;


	public ResourceNotFoundException(String resourceName, String resourceId, Object resourceValue) {
		super(String.format("%s not found with %s : '%s'", resourceName, resourceId, resourceValue));
		this.resourceName = resourceName;
		this.resourceId = resourceId;
		this.resourceValue = resourceValue;
	}
	}

