package exceptions;

public class UsernameInvalidException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameInvalidException(){
        super("The username don't exist");
    }
    
}
