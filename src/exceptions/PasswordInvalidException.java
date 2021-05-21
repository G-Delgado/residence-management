package exceptions;

public class PasswordInvalidException extends Exception {

    public PasswordInvalidException() {
        super("The password is incorrect");
    }

}
