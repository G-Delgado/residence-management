package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import exceptions.*;
import java.util.List;


public class ResidenceManagement {

	public final static String APARTMENT_FILE = "./data/apartments.rm";
	public final static String PET_FILE = "./data/pets.rm";
	public final static String DOORMAN_FILE = "./data/doormans.rm";
	public final static String OWNER_FILE = "./data/owners.rm";
	public final static String ADMIN_FILE = "./data/admins.rm";
	public final static String SERVICESTAFF_FILE = "./data/servicestaff.rm";
	public final static String RESIDENTS_FILE = "data/residents.csv";
	public final static String CARS_FILE = "data/vehicles.csv";

	private final static String SEPARATE = ",";

	private List<Resident> residents;
	private List<Pet> pets;
	private List<Doorman> doormen;
	private List<ServiceStaff> serviceStaff;
	private List<Owner> owners;
	private List<Apartament> apartaments;
	private List<Vehicle> vehicles;
	private Admin admin;
	private int towers;
	private int floors;
	private int apartamentsPerFloor;
	private double administrationFee;

	public ResidenceManagement(int towers, int floors, int apartamentsPerFloor,double administrationFee) {
		this.residents = new ArrayList<Resident>();
		this.pets = new ArrayList<Pet>();
		this.doormen = new ArrayList<Doorman>();
		this.owners = new ArrayList<Owner>();
		this.apartaments = new ArrayList<Apartament>();
		this.vehicles = new ArrayList<Vehicle>();
		this.admin = new Admin("root", "1234");
		this.towers = towers;
		this.floors = floors;
		this.apartamentsPerFloor = apartamentsPerFloor;
		createApartaments(towers, floors, apartamentsPerFloor);
		this.administrationFee=administrationFee;
		try {
			importDataResidents();
			importDataCars();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	// For tests
	public ResidenceManagement(int apartamentsPerFloor, double administrationFee) {
		this.residents = new ArrayList<Resident>();
		this.pets = new ArrayList<Pet>();
		this.doormen = new ArrayList<Doorman>();
		this.owners = new ArrayList<Owner>();
		this.apartaments = new ArrayList<Apartament>();
		this.vehicles = new ArrayList<Vehicle>();
		this.admin = new Admin("root", "1234");
		this.apartamentsPerFloor = apartamentsPerFloor;
		this.administrationFee=administrationFee;
	}

	public void createApartaments(int towers, int floors, int apartamentsPerFloor) {

		for (int i = 1; i <= towers; i++) {
			for (int j = 1; j <= floors; j++) {
				for (int k = 1; k <= apartamentsPerFloor; k++) {
					apartaments.add(new Apartament(i + "", j + "0" + k));

				}
			}
		}

		//System.out.println(apartaments);
	}
	
	public void generateResidentsCsv() {
		try {
			PrintWriter pw = new PrintWriter("./data/residents.csv");
			for (Apartament ap : apartaments) {
					pw.println(ap.toString() + "," + ap.residentsToString());
			}
			
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public void setAdministrationFee(double fee) {
		administrationFee = fee;
	}
	
	public double getAdministrationFee() {
		return administrationFee;
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


	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}



	// IMPORT AND EXPORT RESIDENT-CARS
	public void importDataResidents() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(RESIDENTS_FILE));
		br.readLine();
		String line = br.readLine();

		while (line != null) {
			String[] parts = line.split(SEPARATE);
			String firstName = parts[0];
			String lastName = parts[1];
			int phone = Integer.parseInt(parts[2]);
			String id = parts[3];

			residents.add(new Resident(firstName, lastName, phone, id));
			line = br.readLine();
		}
		br.close();
	}

	public void importDataCars() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(CARS_FILE));
		br.readLine();
		String line = br.readLine();

		while (line != null) {
			String[] parts = line.split(SEPARATE);
			String license = parts[0];
			String type = parts[1];
			if (type.equals("CAR")){
				vehicles.add(new Car(license));
			}else{
				vehicles.add(new Motorcycle(license));
			}
			line = br.readLine();
		}
		br.close();
		System.out.println(vehicles.toString());
	}

	public void exportResident() throws FileNotFoundException {
		// residents.add(new Resident("Juan","Garcia",34323,"10033"));
		PrintWriter pw = new PrintWriter(RESIDENTS_FILE);

		String report = "first_name" + SEPARATE + "last_name" + SEPARATE + "phone" + SEPARATE + "id";

		for (Resident resident : residents) {
			report += "\n" + resident.toCSV(SEPARATE);
		}

		pw.println(report);
		pw.close();

	}

	public void exportCars() throws FileNotFoundException {
		//vehicles.add(new Vehicle("TXT123"));
		PrintWriter pw = new PrintWriter(CARS_FILE);

		String report = "licensePlate";

		for (Vehicle car : vehicles) {
			report += "\n" + car.toCSV();
		}

		pw.println(report);
		pw.close();

	}

	// Login
	public boolean loginAdmin(String username, String password) {
		Admin loginAdmin = new Admin(username, password);
		if (loginAdmin.equals(admin))
			return true;
		else
			return false;
	}

	public boolean loginApartament(String username,String password) throws UsernameInvalidException,PasswordInvalidException{
		boolean login=false;

		String number="1";
		String tower="1";

		if (username.contains("_")){
		 number=username.split("_")[0];
		 tower=username.split("_")[1];
		}
		Apartament apto=new Apartament(tower, number, username,password);
		int search=binarySearchApartament(apartaments, apto);
		if (search>=0){
			if (apto.equals(apartaments.get(search))){
				login=true;
			}else{
				throw new PasswordInvalidException();
			}
		}else{
			throw new UsernameInvalidException();
		}
		
		return login;

	}


	public int binarySearchApartament(List<Apartament> apartaments,Apartament key){
		//FUNCIONA PERO REVISAR ESTO
		int low = 0;
        int high = apartaments.size()-1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<Apartament> midVal = apartaments.get(mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid;
        }
        return -(low + 1); 
	}


	//Generate invoices

	public void generateAdministrationDebt(Date date){
		String description="Cuota de administracion";

		for (Apartament apartament : apartaments) {
			generateDebt(description, date, administrationFee,apartament);
		}
		

	}

	//Sorts
	
	public void bubbleSortDoormen() {
		// Sort Doormen!
		for (int i = 0; i < doormen.size(); i++) {
			for (int j = 0; j < doormen.size(); j++) {
				if (doormen.get(i).compareTo(doormen.get(j)) < 0) {
					Doorman alt = doormen.get(i);
					doormen.set(i, doormen.get(j));
					doormen.set(j, alt);
				}
			}
		}
	}


	public void insertionSortResidents(){
		 int n = residents.size();
        for (int i = 1; i < n; ++i) {
            Resident key = residents.get(i);
            int j = i - 1;
            while (j >= 0 && residents.get(j).compareTo(key) <0) {
                residents.set(j+1,residents.get(j));
                j = j - 1;
            }
			residents.set(j+1,key);
        }
	}


	public void selectionSortServiceStaff(){
		int n = serviceStaff.size();

        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (serviceStaff.get(j).compareTo(serviceStaff.get(min_idx)) < 0){
                    min_idx = j;
				}
            ServiceStaff temp = serviceStaff.get(min_idx);
			serviceStaff.set(min_idx, serviceStaff.get(i));
			serviceStaff.set(i,temp);
        }
	}


	
	public void binarySearchPets(String petName) {
		Pet found = null;
		int i = 0;
		int j = pets.size();
		int m = 0;
		while (i <= j) {
			m = (j + i)/2;
			if (pets.get(m).getName().equals(petName)) {
				found = pets.get(m);
			} else if (pets.get(m).getName().compareTo(petName) < 0) {
				j = m - 1;
			} else {
				i = m + 1;
			}
		}
		System.out.println(found);
	}


	public void generateDebt(String description,Date date,double price,Apartament apartament){	
		apartament.getDebt().add(new Debt( description,  price,  date));
		apartament.calculateTotalDebt();
	}

	public void exportResidentsPerApartaments(String apto,File file) throws FileNotFoundException{
		
			String number=apto.split("_")[0];
			String tower=apto.split("_")[1];
			Apartament apartament=new Apartament(tower, number, apto,"");
			int index=binarySearchApartament(apartaments, apartament);

		   apartament=apartaments.get(index);
		   

		PrintWriter pw = new PrintWriter(file);

		String export="FIRST NAME, LAST NAME, PHONE NUMBER, ID";

		apartament.setResidents(residents);

		for (Resident resident : apartament.getResidents()) {
			export+="\n"+resident.toCSV(SEPARATE);
		}
		pw.print(export);
		pw.close();
	}
}
