package it.istat.is2.logger.exceptions;

public class WorkSessionNotFoundException extends Exception {

 
	private static final long serialVersionUID = -3650768018734046819L;

	public WorkSessionNotFoundException(String message) {
        super(message);
    }

    public WorkSessionNotFoundException() {
    }
}
