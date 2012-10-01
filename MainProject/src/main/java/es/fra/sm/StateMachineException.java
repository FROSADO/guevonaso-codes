package es.fra.sm;

public class StateMachineException extends RuntimeException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5251854165354709056L;

	public StateMachineException() {
		super();

	}

	public StateMachineException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StateMachineException(String message, Throwable cause) {
		super(message, cause);
	}

	public StateMachineException(String message) {
		super(message);
	}

	public StateMachineException(Throwable cause) {
		super(cause);
	}

}
