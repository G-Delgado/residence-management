package model;

import java.io.Serializable;

public class Doorman extends Person implements Serializable, Comparable<Doorman>{
// Doorman still extends from Person
    /**
	 * 
	 */
	private static final long serialVersionUID = 1;

	public Doorman(String firstName, String lastName, int phoneNumber, String id) {
        super(firstName, lastName, phoneNumber, id);
    }

	@Override
	public int compareTo(Doorman o) {
		int result = getLastName().compareTo(o.getLastName());
		if (result == 0) {
			result = getFirstName().compareTo(o.getFirstName());
		}
		return result;
	}
    
}
