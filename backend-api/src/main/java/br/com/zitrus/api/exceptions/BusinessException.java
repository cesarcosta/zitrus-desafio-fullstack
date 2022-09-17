package br.com.zitrus.api.exceptions;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5506376107273122630L;

	public BusinessException(String message) {
		super(message);
	}
}