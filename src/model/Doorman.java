package model;

import java.io.Serializable;

public class Doorman extends Person implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1;

	public Doorman(String firstName, String lastName, int phoneNumber, String id) {
        super(firstName, lastName, phoneNumber, id);
    }
    
}
