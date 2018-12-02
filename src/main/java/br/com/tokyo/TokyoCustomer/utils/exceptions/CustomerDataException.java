package br.com.tokyo.TokyoCustomer.utils.exceptions;

public class CustomerDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomerDataException() {
		super();
	}

	public CustomerDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerDataException(String message) {
		super(message);
	}
}
