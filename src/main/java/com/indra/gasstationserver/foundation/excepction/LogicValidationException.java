package com.indra.gasstationserver.foundation.excepction;

public class LogicValidationException extends RuntimeException{

	private static final long serialVersionUID = -4975604496014502130L;

	public LogicValidationException (String message) {
		super(message);
	}
}
