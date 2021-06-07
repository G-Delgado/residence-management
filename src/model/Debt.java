package model;

import java.time.LocalDate;

public class Debt {

    private String description;
    private double price;
    private String date;



    public Debt(String description, double price, LocalDate date) {
        this.description = description;
        this.price = price;
        this.date=date.getYear()+"/"+date.getMonth()+"/"+date.getDayOfMonth();

    }



    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
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
            "}";
    }


    
}
