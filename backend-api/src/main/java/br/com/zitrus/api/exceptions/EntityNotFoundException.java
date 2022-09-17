package br.com.zitrus.api.exceptions;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3024126245224207048L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
