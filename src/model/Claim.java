package model;

public class Claim {

    private String subject;
    private String description;
    //Add files
    private Apartament sender;
    private Claim next;




    public Claim(String subject, String description, Apartament sender) {
        this.subject = subject;
        this.description = description;
        this.sender = sender;
    }
    
    


    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Apartament getSender() {
        return this.sender;
    }

    public void setSender(Apartament sender) {
        this.sender = sender;
    }

    public Claim getNext() {
        return this.next;
    }

    public void setNext(Claim next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "{" +
            " subject='" + getSubject() + "'" +
            ", description='" + getDescription() + "'" +
            ", sender='" + getSender() + "'" +
            ", next='" + getNext() + "'" +
            "}";
    }



    
}
