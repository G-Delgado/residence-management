package model;

import java.io.Serializable;

public class ServiceStaff extends Person implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1;

	public ServiceStaff(String firstName, String lastName, int phoneNumber, String id) {
        super(firstName, lastName, phoneNumber, id);
    }

    
    
}
