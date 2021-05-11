package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class ResidenceManagement {
	
	private final static String APARTMENT_FILE = "./data/apartments.rm";
	private final static String	PET_FILE = "./data/pets.rm";
	private final static String DOORMAN_FILE = "./data/doormans.rm";
	private final static String OWNER_FILE = "./data/owners.rm";
	private final static String ADMIN_FILE = "./data/admins.rm";
	private final static String SERVICESTAFF_FILE = "./data/servicestaff.rm";

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
    
    @SuppressWarnings("unchecked")
	public void loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
    	ObjectInputStream ois = null;
    	File f = new File(APARTMENT_FILE);
    	if (f.exists()) {
    		ois = new ObjectInputStream(new FileInputStream(f));
    		apartaments = (ArrayList<Apartament>) ois.readObject();
    		ois.close();
    	}
    	
    	
    	f = new File(PET_FILE);
    	if (f.exists()) {
    		ois = new ObjectInputStream(new FileInputStream(f));
    		pets = (ArrayList<Pet>) ois.readObject();
    		ois.close();
    	}
    	
    	f = new File(DOORMAN_FILE);
    	if (f.exists()) {
    		ois = new ObjectInputStream(new FileInputStream(f));
    		doormen = (ArrayList<Doorman>) ois.readObject();
    		ois.close();
    	}
    	
    	f = new File(OWNER_FILE);
    	if (f.exists()) {
    		ois = new ObjectInputStream(new FileInputStream(f));
    		owners = (ArrayList<Owner>) ois.readObject();
    		ois.close();
    	}
    	
    	f = new File(ADMIN_FILE);
    	if (f.exists()) {
    		ois = new ObjectInputStream(new FileInputStream(f));
    		admin = (Admin) ois.readObject();
    		ois.close();
    	}
    	
    	f = new File(SERVICESTAFF_FILE);
    	if (f.exists()) {
    		ois = new ObjectInputStream(new FileInputStream(f));
    		serviceStaff = (ArrayList<ServiceStaff>) ois.readObject();
    		ois.close();
    	}
    }
    
    public void saveApartments() throws FileNotFoundException, IOException {
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(APARTMENT_FILE));
    	oos.writeObject(apartaments);
    	oos.close();
    }
    
	public void savePets() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PET_FILE));
    	oos.writeObject(pets);
    	oos.close();	
	}
	
	public void saveDoormans() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DOORMAN_FILE));
    	oos.writeObject(doormen);
    	oos.close();
	}
	
	public void saveOwners() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OWNER_FILE));
    	oos.writeObject(owners);
    	oos.close();
	}
	
	public void saveAdmins() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ADMIN_FILE));
    	oos.writeObject(admin);
    	oos.close();
	}
	
	public void saveServiceStaff() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERVICESTAFF_FILE));
    	oos.writeObject(serviceStaff);
    	oos.close();
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
