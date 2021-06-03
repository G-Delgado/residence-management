package model;

public class Resident extends Person implements Comparable<Resident>{

    public Resident(String firstName, String lastName, int phoneNumber, String id) {
        super( firstName, lastName, phoneNumber, id);
        
    }


    public String toCSV(String separate){
        return getFirstName()+separate+getLastName()+separate+getPhoneNumber()+separate+getId();
    }

    
    @Override
	public int compareTo(Resident o) {
		int result = getLastName().compareTo(o.getLastName());
		if (result == 0) {
			result = getPhoneNumber() - o.getPhoneNumber() ;
		}
		return result;
	}

}
