package model;

public abstract class Person {

    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String id;


    public Person(String firstName, String lastName, int phoneNumber, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public Person() {
        this.firstName = null;
        this.lastName = null;
        this.phoneNumber =0;
        this.id = null;
    }



    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "{" +
            " firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", id='" + getId() + "'" +
            "}";
    }
    
    
    public String toStringJavaFX() {
    	return getFirstName() + " " + getLastName(); 
    }


}


