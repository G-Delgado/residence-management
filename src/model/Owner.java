package model;

public class Owner extends Person{

    private String mail;


    public Owner(String firstName, String lastName, int phoneNumber, String id,String mail) {
        super(firstName, lastName, phoneNumber, id);
        this.mail = mail;
        
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


}
    

