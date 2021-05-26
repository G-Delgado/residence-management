package model;

import java.util.Date;

public class Debt {

    private String description;
    private double price;
    private Date date;



    public Debt(String description, double price, Date date) {
        this.description = description;
        this.price = price;
        this.date = date;
    }


    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "{" +
            " description='" + getDescription() + "'" +
            ", price='" + getPrice() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }


    
}
