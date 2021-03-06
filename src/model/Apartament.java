package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Apartament implements Serializable, Comparable<Apartament> {

    /**
     * 
     */
    private static final long serialVersionUID = 1;

    private String tower;
    private int number;
    private Owner owner;
    private List<Resident> residents;
    private List<Pet> pets;
    private List<Vehicle> cars;
    private String username;
    private String password;
    private List<Debt> debt;
    private double totalDebt;
    
    // Binary Tree relations
    private Apartament left;
    private Apartament right;
    private Apartament parent;

    public Apartament(String tower, String number) {
        this.tower = tower;
        this.number = Integer.parseInt(number);
        this.owner = null;
        this.residents = new ArrayList<Resident>();
        this.pets = new ArrayList<Pet>();
        this.cars = new ArrayList<Vehicle>();
        this.username = number + "_" + tower;
        this.password = "1234";
        this.debt=new ArrayList<Debt>();
        left = null;
        right = null;
        parent = null;
    }

    public Apartament(String tower, String number, String username, String password) {
        this.tower = tower;
        this.number = Integer.parseInt(number);
        this.username = username;
        this.password = password;
    }

    public String getTower() {
        return this.tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
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

    public List<Vehicle> getCars() {
        return this.cars;
    }

    public void setCars(List<Vehicle> cars) {
        this.cars = cars;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public List<Debt> getDebt() {
        return this.debt;
    }

    public void setDebt(List<Debt> debt) {
        this.debt = debt;
    }
    
    public String residentsToString() {
    	String result = "";
    	for (int i = 0; i < residents.size(); i++) {
    		if (i == residents.size() - 1) {
    			result += residents.get(i).toCSV(",");
    		} else {
    			result += residents.get(i).toCSV(",") + ",";
    		}
    	}
    	
    	return result;
    }
    
    public double getTotalDebt() {
    	return totalDebt;
    }
    
    public void calculateTotalDebt() {
    	double total = 0;
    	for (Debt d : debt) {
    		total += d.getPrice();
    	}
    	
    	totalDebt = total;
    }
    
    public void setLeft(Apartament ap) {
    	left = ap;
    }
    
    public Apartament getLeft() {
    	return left;
    }
    
    public void setRight(Apartament ap) {
    	left = ap;
    }
    
    public Apartament getRight() {
    	return right;
    }
    
    public void setParent(Apartament ap) {
    	left = ap;
    }
    
    public Apartament getParent() {
    	return parent;
    }
    

    @Override
    public String toString() {
        return "{" + " tower='" + getTower() + "'" + ", number='" + getNumber() + "'" + "}\n";
    }

    @Override
    public int compareTo(Apartament o) {
        String towerO = o.getTower();
        int numberO = o.getNumber();

        if (tower.compareTo(towerO) == 0) {
            return Integer.compare(number, numberO);
        } else {
            return tower.compareTo(towerO);
        }

    }

    @Override
    public boolean equals(Object o) {
        Apartament obj = (Apartament) o;
        if (this.username.equals(obj.getUsername()) && this.password.equals(obj.getPassword()))
            return true;
        else
            return false;
    }

}
