package model;

public class ApartmentDebts {
	private Apartament root;
	
	public ApartmentDebts() {
		root = null;
	}
	
	public void addApartament(Apartament ap, int indicator) {
		if (indicator == 1) {			
			if (root == null) {
				root = ap;
			} else {
				addApartamentRecursive(root, ap);
			}
		}
	}
	
	private void addApartamentRecursive(Apartament actual, Apartament ap) {
		if (ap.getTotalDebt() <= actual.getTotalDebt()) {
			if (actual.getLeft() == null) {
				actual.setLeft(ap);
				ap.setParent(actual);
			} else {
				addApartamentRecursive(actual.getLeft(), ap);
			}
		} else if (ap.getTotalDebt() > actual.getTotalDebt()) {
			if (actual.getRight() == null) {
				actual.setRight(ap);
			} else {
				addApartamentRecursive(actual.getRight(), ap);
				ap.setParent(actual);
			}
		}
	}
	
	public Apartament searchApartamentByDebt(double ap) {
		Apartament found = null;
		if (root == null) {
			found = null;
		} else if (root.getTotalDebt() == ap) {
			found = root;
		} else {
			found = searchApartamentByDebtRecursive(root, ap, found);
		}
		return found;
	}
	
	private Apartament searchApartamentByDebtRecursive(Apartament actual, double ap, Apartament found) {
		if (ap < actual.getTotalDebt()) {
			if (actual.getLeft() != null) {
				return searchApartamentByDebtRecursive(actual.getLeft(), ap, found);
			} else {
				return null;
			}
		} else if (ap > actual.getTotalDebt()){
			if (actual.getRight() != null) {
				return searchApartamentByDebtRecursive(actual.getRight(), ap, found);
			} else {
				return null;
			}
		} else {
			found = actual;
			return found;
		}
		
	}
	
	
}
