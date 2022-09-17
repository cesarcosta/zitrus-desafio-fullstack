package br.com.zitrus.api.exceptions;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5506376107273122630L;

	public BusinessException(String message) {
		super(message);
	}
}