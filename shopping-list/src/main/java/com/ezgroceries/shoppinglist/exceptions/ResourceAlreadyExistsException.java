package com.ezgroceries.shoppinglist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason="The resource already exists")
public class ResourceAlreadyExistsException extends RuntimeException {}
