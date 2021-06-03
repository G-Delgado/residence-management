package model;

import java.io.Serializable;

public class ServiceStaff extends Person implements Serializable,Comparable<ServiceStaff>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1;

	public ServiceStaff(String firstName, String lastName, int phoneNumber, String id) {
        super(firstName, lastName, phoneNumber, id);
    }

	@Override
	public int compareTo(ServiceStaff o) {
		int result = getId().compareTo(o.getId());
		if (result == 0) {
			result = getLastName().compareTo(o.getLastName());
		}
		return result;
	}
    
}
