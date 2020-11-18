package it.istat.is2.design.exceptions;

public class NoDataException extends RuntimeException {
  
	private static final long serialVersionUID = -7199190414444552771L;

	public NoDataException(String message) {
        super(message);
    }
}
