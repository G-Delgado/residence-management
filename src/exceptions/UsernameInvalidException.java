package exceptions;

public class UsernameInvalidException extends Exception{

    public UsernameInvalidException(){
        super("The username don't exist");
    }
    
}
