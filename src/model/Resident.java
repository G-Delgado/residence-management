package model;

public class Resident extends Person {

    public Resident(String firstName, String lastName, int phoneNumber, String id) {
        super( firstName, lastName, phoneNumber, id);
        
    }


    public String toCSV(String separate){
        return getFirstName()+separate+getLastName()+separate+getPhoneNumber()+separate+getId();
    }

    
    
}
