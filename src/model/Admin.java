package model;

public class Admin extends Person{

    private String user;
    private String password;

    public Admin(String firstName, String lastName, int phoneNumber, String id) {
        super(firstName, lastName, phoneNumber, id);
        this.user=null;
        this.password=null;
    }


    public Admin(String user,String password){
        super();
        this.user=user;
        this.password=password;
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



    
}
