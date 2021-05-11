package model;

import java.util.ArrayList;
import java.util.List;


public class ResidenceManagement {

    private List<Resident> residents;
    private List<Pet> pets;
    private List<Doorman> doormen;
    private List<ServiceStaff> serviceStaff;
    private List<Owner> owners;
    private List<Apartament> apartaments;
    private Admin admin;
    private int towers;
    private int floors;
    private int apartamentsPerFloor;


    public ResidenceManagement(Admin admin, int towers, int floors, int apartamentsPerFloor) {
        this.residents = new ArrayList<Resident>();
        this.pets = new ArrayList<Pet>();
        this.doormen = new ArrayList<Doorman>();
        this.owners = new ArrayList<Owner>();
        this.apartaments = new ArrayList<Apartament>();
        this.admin = admin;
        this.towers = towers;
        this.floors = floors;
        this.apartamentsPerFloor = apartamentsPerFloor;
        createApartaments(towers,floors,apartamentsPerFloor);

    }

    private void createApartaments(int towers, int floors, int apartamentsPerFloor) {

        for (int i = 1; i <= towers; i++) {
            for (int j = 1; j <= floors; j++) {
                for (int k = 1; k <= apartamentsPerFloor; k++) {
                    apartaments.add(new Apartament(i + "", j + "0" + k, null, null, null, null));

                }
            }
        }

        System.out.println(apartaments);
    }

	public List<Resident> getResidents() {
		return residents;
	}

	public void setResidents(List<Resident> residents) {
		this.residents = residents;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<Doorman> getDoormen() {
		return doormen;
	}

	public void setDoormen(List<Doorman> doormen) {
		this.doormen = doormen;
	}

	public List<ServiceStaff> getServiceStaff() {
		return serviceStaff;
	}

	public void setServiceStaff(List<ServiceStaff> serviceStaff) {
		this.serviceStaff = serviceStaff;
	}

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

	public List<Apartament> getApartaments() {
		return apartaments;
	}

	public void setApartaments(List<Apartament> apartaments) {
		this.apartaments = apartaments;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getTowers() {
		return towers;
	}

	public void setTowers(int towers) {
		this.towers = towers;
	}

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	public int getApartamentsPerFloor() {
		return apartamentsPerFloor;
	}

	public void setApartamentsPerFloor(int apartamentsPerFloor) {
		this.apartamentsPerFloor = apartamentsPerFloor;
	}
    
    

}
