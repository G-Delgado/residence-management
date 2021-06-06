package model;

import java.time.LocalDateTime;

public class Reservation {


    private CommonZones place;
    private LocalDateTime init;
    private LocalDateTime finish;
    private Reservation next;

    public Reservation(CommonZones place, LocalDateTime init, LocalDateTime finish) {
        this.place = place;
        this.init = init;
        this.finish = finish;
    }


    public CommonZones getPlace() {
        return this.place;
    }

    public void setPlace(CommonZones place) {
        this.place = place;
    }

    public LocalDateTime getInit() {
        return this.init;
    }

    public void setInit(LocalDateTime init) {
        this.init = init;
    }

    public LocalDateTime getFinish() {
        return this.finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public Reservation getNext() {
        return this.next;
    }

    public void setNext(Reservation next) {
        this.next = next;
    }



    @Override
    public String toString() {
        return "{" +
            " place='" + getPlace() + "'";
    }



   

    
    
}
