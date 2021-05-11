package model;

import java.io.Serializable;

public class Owner extends Person implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1;
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
    

