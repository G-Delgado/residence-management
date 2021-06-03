package model;

public class StaffById {
	private ServiceStaff root;
	
	public StaffById() {
		
	}
	
	public void addServiceStaff(ServiceStaff ss) {
		if (root == null) {
			root = ss;
		} else {
			addServiceStaffRecursive(root, ss);
		}
	}
	
	private void addServiceStaffRecursive(ServiceStaff actual, ServiceStaff ss) {
		if (ss.getId().compareTo(actual.getId()) >= 0) {
			if (actual.getLeft() == null) {
				actual.setLeft(ss);
				ss.setParent(actual);
			} else {
				addServiceStaffRecursive(actual.getLeft(), ss);
			}
		} else {
			if (actual.getRight() == null) {
				actual.setRight(ss);
				ss.setParent(actual);
			} else {
				addServiceStaffRecursive(actual.getRight(), ss);
			}
		}
	}
	
	public ServiceStaff searchStaffById(String id) {
		ServiceStaff found = null;
		if (root == null) {
			found = null;
		} else {
			found = searchStaffByIdRecursive(root, id, found);
		}
		
		return found;
	}
	
	private ServiceStaff searchStaffByIdRecursive(ServiceStaff actual, String id, ServiceStaff found) {
		if (id.compareTo(actual.getId()) > 0) {
			if (actual.getLeft() != null) {
				return searchStaffByIdRecursive(actual.getLeft(), id, found);
			} else {
				return null;
			}
		} else if (id.compareTo(actual.getId()) < 0) {
			if (actual.getRight() != null) {
				return searchStaffByIdRecursive(actual.getRight(), id, found);
			} else {
				return null;
			}
		} else {
			found = actual;
			return found;
		}
	}
}
