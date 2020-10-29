package it.istat.is2.logger.exceptions;

public class NULLUserException extends Exception {

 
	private static final long serialVersionUID = -3650768058734046819L;

	public NULLUserException(String message) {
        super(message);
    }

    public NULLUserException() {
    }
}
