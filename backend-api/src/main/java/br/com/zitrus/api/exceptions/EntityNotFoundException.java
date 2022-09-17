package br.com.zitrus.api.exceptions;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3024126245224207048L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
