package exceptions;

public class ReservationsInvalidException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReservationsInvalidException() {
        super("The reservation is invalid");
    }

}
