package model;

import java.io.Serializable;

public class Admin extends Person implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1;

    private String user;
    private String password;

    public Admin(String firstName, String lastName, int phoneNumber, String id) {
        super(firstName, lastName, phoneNumber, id);
        this.user = "Root";
        this.password = "1234";
    }

    public Admin(String user, String password) {
        super();
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        Admin obj = (Admin) o;
        if (this.user.equals(obj.getUser()) && this.password.equals(obj.getPassword()))
            return true;
        else
            return false;

    }

}
