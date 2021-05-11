package model;

import java.util.List;

public class Apartament {

    private String tower;
    private String number;
    private Owner owner;
    private List<Resident> residents;
    private List<Pet> pets;
    private List<Car>cars;
    

    public Apartament(String tower, String number, Owner owner, List<Resident> residents, List<Pet> pets,List<Car> cars) {
        this.tower = tower;
        this.number = number;
        this.owner = owner;
        this.residents = residents;
        this.pets = pets;
        this.cars=cars;
    }



    public String getTower() {
        return this.tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Resident> getResidents() {
        return this.residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public List<Pet> getPets() {
        return this.pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }




    public List<Car> getCars() {
        return this.cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }


    @Override
    public String toString() {
        return "{" +
            " tower='" + getTower() + "'" +
            ", number='" + getNumber() + "'" +
            "}\n";
    }


}
