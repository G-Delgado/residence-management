package model;

import java.time.LocalDate;

public class Reservation {


    private CommonZones place;
    private LocalDate init;
    private Reservation next;

    public Reservation(CommonZones place, LocalDate init) {
        this.place = place;
        this.init = init;
    }


    public CommonZones getPlace() {
        return this.place;
    }

    public void setPlace(CommonZones place) {
        this.place = place;
    }


    public Reservation getNext() {
        return this.next;
    }

    public void setNext(Reservation next) {
        this.next = next;
    }
    
    public LocalDate getInit() {
    	return init;
    }



    @Override
    public String toString() {
        return place + " " + init.toString();
    }



   

    
    
}
