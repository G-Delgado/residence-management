package model;

import java.io.Serializable;

public class ServiceStaff extends Person implements Serializable,Comparable<ServiceStaff>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	// Atttributes for binary tree
	private ServiceStaff left;
	private ServiceStaff right;
	private ServiceStaff parent;

	public ServiceStaff(String firstName, String lastName, int phoneNumber, String id) {
        super(firstName, lastName, phoneNumber, id);
    }

	
	
	public ServiceStaff getLeft() {
		return left;
	}
	
	public void setLeft(ServiceStaff left) {
		this.left = left;
	}

	public ServiceStaff getRight() {
		return right;
	}

	public void setRight(ServiceStaff right) {
		this.right = right;
	}

	public ServiceStaff getParent() {
		return parent;
	}

	public void setParent(ServiceStaff parent) {
		this.parent = parent;
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
