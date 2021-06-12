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
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.util.List;

import javax.swing.WindowConstants;

public class ResidenceManagement {

	public final static String APARTMENT_FILE = "./data/apartments.rem";
	public final static String PET_FILE = "./data/pets.rem";
	public final static String DOORMAN_FILE = "./data/doormans.rem";
	public final static String OWNER_FILE = "./data/owners.rem";
	public final static String ADMIN_FILE = "./data/admins.rem";
	public final static String SERVICESTAFF_FILE = "./data/servicestaff.rem";
	public final static String RESIDENTS_FILE = "data/residents.csv";
	public final static String CARS_FILE = "data/vehicles.csv";

	private final static String SEPARATE = ",";

	private ArrayList<Resident> residents;
	private ArrayList<Pet> pets;
	private ArrayList<Doorman> doormen;
	private ArrayList<ServiceStaff> serviceStaff;
	private ArrayList<Owner> owners;
	private ArrayList<Apartament> apartaments;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<CommonZones> commonZones;
	private Admin admin;
	private int towers;
	private int floors;
	private int apartamentsPerFloor;
	private double administrationFee;
	private Claim rootClaim;
	private Reservation rootReservation;
	ApartmentDebts apartmentPerDebt;
	StaffById serviceById;

	public ResidenceManagement(int towers, int floors, int apartamentsPerFloor, double administrationFee) {
		this.residents = new ArrayList<Resident>();
		this.pets = new ArrayList<Pet>();
		this.doormen = new ArrayList<Doorman>();
		this.owners = new ArrayList<Owner>();
		this.apartaments = new ArrayList<Apartament>();
		this.vehicles = new ArrayList<Vehicle>();
		this.serviceStaff = new ArrayList<ServiceStaff>();
		this.admin = new Admin("root", "1234");
		this.towers = towers;
		this.floors = floors;
		this.commonZones=setupCommonZones();
		this.apartamentsPerFloor = apartamentsPerFloor;
		createApartaments(towers, floors, apartamentsPerFloor);
		this.administrationFee = administrationFee;
		apartmentPerDebt = new ApartmentDebts();
		serviceById = new StaffById();
		try {
			importDataResidents();
			importDataCars();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<CommonZones> setupCommonZones() {
		CommonZones littlePool=new Pool("Pool for childrens", 10);
		CommonZones bigPool=new Pool("Pool", 20);
		CommonZones kiosk=new Kiosk("Main Kiosk", 30);
		CommonZones park=new Park("Park", 15);
		ArrayList<CommonZones> list=new ArrayList<>();
		list.add(littlePool);
		list.add(bigPool);
		list.add(kiosk);
		list.add(kiosk);
		list.add(park);
		return list;
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
		this.administrationFee = administrationFee;
	}

	public void createApartaments(int towers, int floors, int apartamentsPerFloor) {

		for (int i = 1; i <= towers; i++) {
			for (int j = 1; j <= floors; j++) {
				for (int k = 1; k <= apartamentsPerFloor; k++) {
					apartaments.add(new Apartament(i + "", j + "0" + k));

				}
			}
		}

		// System.out.println(apartaments);
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

	/*@SuppressWarnings("unchecked")
	public void loadD() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = null;
		File f = new File(DOORMAN_FILE);
		if (f.exists()) {
			ois = new ObjectInputStream(new FileInputStream(f));
			doormen = (ArrayList<Doorman>) ois.readObject();
			System.out.println(doormen);
			System.out.println(doormen.size());
			System.out.println(doormen.get(0));
			ois.close();
		}

	}*/
	
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
		
		System.out.println("Saved!");
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

	public void setResidents(ArrayList<Resident> residents) {
		this.residents = residents;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(ArrayList<Pet> pets) {
		this.pets = pets;
	}

	public ArrayList<Doorman> getDoormen() {
		return doormen;
	}

	public void setDoormen(ArrayList<Doorman> doormen) {
		this.doormen = doormen;
	}

	public void setAdministrationFee(double fee) {
		administrationFee = fee;
	}

	public double getAdministrationFee() {
		return administrationFee;
	}

	public ArrayList<ServiceStaff> getServiceStaff() {
		return serviceStaff;
	}

	public void setServiceStaff(ArrayList<ServiceStaff> serviceStaff) {
		this.serviceStaff = serviceStaff;
	}

	public ArrayList<Owner> getOwners() {
		return owners;
	}

	public void setOwners(ArrayList<Owner> owners) {
		this.owners = owners;
	}

	public ArrayList<Apartament> getApartaments() {
		return apartaments;
	}

	public void setApartaments(ArrayList<Apartament> apartaments) {
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

	public ArrayList<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Reservation getRootReservation() {
		return this.rootReservation;
	}

	public void setRootReservation(Reservation rootReservation) {
		this.rootReservation = rootReservation;
	}


	public Claim getRootClaim() {
		return this.rootClaim;
	}

	public void setRootClaim(Claim rootClaim) {
		this.rootClaim = rootClaim;
	}


	public ArrayList<CommonZones> getCommonZones() {
		return this.commonZones;
	}

	public void setCommonZones(ArrayList<CommonZones> commonZones) {
		this.commonZones = commonZones;
	}


	public void importDataDoorman(String directory) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(directory));
		br.readLine();
		String line = br.readLine();

		while (line != null) {
			String[] parts = line.split(SEPARATE);
			String firstName = parts[0];
			String lastName = parts[1];
			int phone = Integer.parseInt(parts[2]);
			String id = parts[3];

			doormen.add(new Doorman(firstName, lastName, phone, id));
			line = br.readLine();
		}
		br.close();
	}

	public void importDataserviceStaff(String directory) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(directory));
		br.readLine();
		String line = br.readLine();

		while (line != null) {
			String[] parts = line.split(SEPARATE);
			String firstName = parts[0];
			String lastName = parts[1];
			int phone = Integer.parseInt(parts[2]);
			String id = parts[3];

			serviceStaff.add(new ServiceStaff(firstName, lastName, phone, id));
			line = br.readLine();
		}
		br.close();
	}

	public void importDataOwners(String directory) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(directory));
		br.readLine();
		String line = br.readLine();

		while (line != null) {
			String[] parts = line.split(SEPARATE);
			String firstName = parts[0];
			String lastName = parts[1];
			int phone = Integer.parseInt(parts[2]);
			String id = parts[3];

			owners.add(new Owner(firstName, lastName, phone, id,"Prueba@gmail.com"));
			line = br.readLine();
		}
		br.close();
	}

	
	// Add, Edit and Delete objects --------------------------------
	
	public void addAdmin(String fn, String ln, int pn, String id) {
		admin = new Admin(fn, ln, pn, id);
	}
	
	public void editAdmin(String fn, String ln, int pn, String id) {
		if (!fn.equals(admin.getFirstName())) {
			admin.setFirstName(fn);
		}
		if (!ln.equals(admin.getLastName())) {
			admin.setLastName(ln);
		}
		if (pn != admin.getPhoneNumber()) {
			admin.setPhoneNumber(pn);
		}
		if (!id.equals(admin.getId())) {
			admin.setId(id);
		}
		
		try {
			saveAdmins();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAdmin() {
		admin = null;
	}
	
	public void addServiceStaff(String fn, String ln, int pn, String id, String type) {
		if (type.equals("SERVICE STAFF")) {
			ServiceStaff ss = new ServiceStaff(fn,ln,pn,id);
			serviceStaff.add(ss);
			serviceById.addServiceStaff(ss);
			
			try {
				saveServiceStaff();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			Doorman d = new Doorman(fn,ln,pn,id);
			doormen.add(d);
			
			try {
				saveDoormans();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ServiceStaff searchServiceStaffById(String id) {
		return serviceById.searchStaffById(id);
	}
	
	public boolean deleteServiceStaff(String id) {
		boolean deleted = false;
		for (int i = 0; i < serviceStaff.size() && !false; i++) {
			if (serviceStaff.get(i).getId().equals(id)) {
				serviceStaff.remove(i);
				deleted = true;
			}
		}
		
		try {
			saveServiceStaff();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return deleted;
	}

	public boolean deleteDoorman(String id) {
		boolean deleted = false;
		for (int i = 0; i < doormen.size() && !false; i++) {
			if (doormen.get(i).getId().equals(id)) {
				doormen.remove(i);
				deleted = true;
			}
		}
		
		try {
			saveDoormans();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return deleted;
	}
	
	public void addResident(Apartament apto, String fn, String ln, int pn, String id) {
		Resident r = new Resident(fn,ln,pn,id);
		residents.add(r);
		apto.getResidents().add(r);
		try {
			exportResident();
			saveApartments();
			// No estamos actualizando residentes en la Lista GENERAL
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteResident(Apartament apto, String id) {
		boolean deleted = false;
		for (int i = 0; i < residents.size() && !deleted; i++) {
			if (residents.get(i).getId().equals(id)) {
				residents.remove(i);
				deleted = true;
			}
		}
		
		deleted = false;
		
		for (int i = 0; i < apto.getResidents().size() && !deleted; i++) {
			if (apto.getResidents().get(i).getId().equals(id)) {
				apto.getResidents().remove(i);
				deleted = false;
			}
		}
		
		try {
			saveApartments();
			exportResident();
			// No estamos actualizando residentes en la Lista GENERAL
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addPet(Apartament apto, String name, String type) {
		Pet p = new Pet(name, type);
		pets.add(p);
		apto.getPets().add(p);
		try {
			savePets();
			saveApartments();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deletePet(Apartament apto, String name, String type) {
		TypePet ty = TypePet.valueOf(type);
		boolean deleted = false;
		for (int i = 0; i < pets.size() && !deleted; i++) {
			if (pets.get(i).getName().equals(name) && pets.get(i).getType().equals(ty)) {
				pets.remove(i);
				deleted = true;
			}
		}
		
		deleted = false;
		
		for (int i = 0; i < apto.getPets().size() && !deleted; i++) {
			if (apto.getPets().get(i).getName().equals(name) && apto.getPets().get(i).getType().equals(ty)) {
				apto.getPets().remove(i);
				deleted = true;
			}
		}
		
		try {
			savePets();
			saveApartments();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addVehicle(Apartament apto, String licensePlate, String type) {
		if (type.equals("CAR")) {
			Car c = new Car(licensePlate);
			vehicles.add(c);
			apto.getCars().add(c);
		} else {
			Motorcycle mc = new Motorcycle(licensePlate);
			vehicles.add(mc);
			apto.getCars().add(mc);
		}
		
		try {
			saveApartments();
			exportCars();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteVehicle(Apartament apto, String licensePlate) {
		boolean deleted = false;
		for (int i = 0; i < vehicles.size() && !deleted; i++) {
			if (vehicles.get(i).getLicensePlate().equals(licensePlate)) {
				vehicles.remove(i);
				deleted = true;
			}
		}
		
		deleted = false;
		
		for (int i = 0; i < apto.getCars().size() && !deleted; i++) {
			if (apto.getCars().get(i).getLicensePlate().equals(licensePlate)) {
				apto.getCars().remove(i);
				deleted = true;
			}
		}
	}
	
	public void addOwner(Apartament apto, String fn, String ln, int pn, String id, String email) {
		Owner on = new Owner(fn,ln,pn,id,email);
		owners.add(on);
		apto.setOwner(on);
		
		try {
			saveApartments();
			saveOwners();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editOwner(Apartament apto, String fn, String ln, int pn, String id, String email) {
		boolean edited = false;
		Owner on = new Owner(fn,ln,pn,id,email);
		for (int i = 0; i < owners.size() && !edited; i++) {
			if (owners.get(i).getId().equals(id)) {
				owners.set(i, on);
				edited = true;
			}
		}
		
		apto.setOwner(on);
		
		try {
			saveApartments();
			saveOwners();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteOwner(Apartament apto, String id) {
		boolean deleted = false;
		for (int i = 0; i < owners.size() && !deleted; i++) {
			if (owners.get(i).getId().equals(id)) {
				owners.remove(i);
				deleted = true;
			}
		}
		
		apto.setOwner(null);
		
		try {
			saveApartments();
			saveOwners();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	////////////////////////////////////////////////////////////////////////


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
			if (type.equals("CAR")) {
				vehicles.add(new Car(license));
			} else {
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
		// vehicles.add(new Vehicle("TXT123"));
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

	public boolean loginApartament(String username, String password)
			throws UsernameInvalidException, PasswordInvalidException {
		boolean login = false;

		String number = "1";
		String tower = "1";

		if (username.contains("_")) {
			number = username.split("_")[0];
			tower = username.split("_")[1];
		}
		Apartament apto = new Apartament(tower, number, username, password);
		int search = binarySearchApartament(apto);
		if (search >= 0) {
			if (apto.equals(apartaments.get(search))) {
				login = true;
			} else {
				throw new PasswordInvalidException();
			}
		} else {
			throw new UsernameInvalidException();
		}

		return login;

	}

	public int binarySearchApartament(Apartament key) {
		// FUNCIONA PERO REVISAR ESTO
		int low = 0;
		int high = apartaments.size() - 1;

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

	// Generate invoices

	public void generateAdministrationDebt(LocalDate date) {
		String description = "Cuota de administracion";

		for (Apartament apartament : apartaments) {
			generateDebt(description, date, administrationFee, apartament);
		}

	}

	public void generateInvoiceForAll(LocalDate date,String description,double price) {

		for (Apartament apartament : apartaments) {
			generateDebt(description, date, price, apartament);
		}

	}
	
	public Apartament searchApartmentByDebt(double debt) {
		return apartmentPerDebt.searchApartamentByDebt(debt);
	}


	// Sort

	public void bubbleSortDoormen() {
		// Sort Doormen!
		for (int i = 0; i < doormen.size(); i++) {
			for (int j = 0; j < doormen.size(); j++) {
				if (doormen.get(i).compareTo(doormen.get(j)) > 0) {
					Doorman alt = doormen.get(i);
					doormen.set(i, doormen.get(j));
					doormen.set(j, alt);
				}
			}
		}
	}

	public void insertionSortResidents() {
		int n = residents.size();
		for (int i = 1; i < n; ++i) {
			Resident key = residents.get(i);
			int j = i - 1;
			while (j >= 0 && residents.get(j).compareTo(key) > 0) {
				residents.set(j + 1, residents.get(j));
				j = j - 1;
			}
			residents.set(j + 1, key);
		}
	}

	public void selectionSortServiceStaff() {
		int n = serviceStaff.size();

		for (int i = 0; i < n - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (serviceStaff.get(j).compareTo(serviceStaff.get(min_idx)) < 0) {
					min_idx = j;
				}
			ServiceStaff temp = serviceStaff.get(min_idx);
			serviceStaff.set(min_idx, serviceStaff.get(i));
			serviceStaff.set(i, temp);
		}
	}

	public Pet binarySearchPets(String petName) {
		Pet found = null;
		int i = 0;
		int j = pets.size() - 1;
		int m = 0;
		while (i <= j && found == null) {
			m = (j + i) / 2;
			if (pets.get(m).getName().equals(petName)) {
				found = pets.get(m);
			} else if (pets.get(m).getName().compareTo(petName) < 0) {
				j = m - 1;
			} else {
				i = m + 1;
			}
		}
		return found;
	}

	public void generateDebt(String description, LocalDate date, double price, Apartament apartament) {
		apartament.getDebt().add(new Debt(description, price, date));
		apartament.calculateTotalDebt();
		if (price != administrationFee) {			
			apartmentPerDebt.addApartament(apartament);
		}
	}

	public void exportResidentsPerApartaments(String apto, File file) throws FileNotFoundException {

		String number = apto.split("_")[0];
		String tower = apto.split("_")[1];
		Apartament apartament = new Apartament(tower, number, apto, "");
		int index = binarySearchApartament(apartament);

		apartament = apartaments.get(index);

		PrintWriter pw = new PrintWriter(file);

		String export = "FIRST NAME, LAST NAME, PHONE NUMBER, ID";

		apartament.setResidents(residents);

		for (Resident resident : apartament.getResidents()) {
			export += "\n" + resident.toCSV(SEPARATE);
		}
		pw.print(export);
		pw.close();
	}

	// Linked list Reservation

	public CommonZones searchCommonZone(String name){

		for (CommonZones z : commonZones) {

			if (z.getName().equals(name)){
				return z;
			}
			
		}

		return null;

	}

	public void addReservation(CommonZones place, LocalDate init) throws ReservationsInvalidException {



		if (init.isBefore(LocalDate.now())){
			throw new ReservationsInvalidException();
		}
		Reservation reservation = new Reservation(place, init);

		if (rootReservation == null) {
			rootReservation = reservation;
		} else {
			Reservation r = rootReservation;
			while (r.getNext() != null) {
				r = r.getNext();
			}
			r.setNext(reservation);
		}
	}

	public void toStringReservation() {
		String message = "";
		Reservation r = rootReservation;
		message += r.toString();
		while (r.getNext() != null) {
			r = r.getNext();
			message += r.toString();
		}
		System.out.println(message);
	}

	//Linked list Claims

	public void addClaim(String subject, String description, Apartament sender) {
		Claim claim = new Claim(subject, description, sender);

		if (rootClaim == null) {
			rootClaim = claim;
		} else {
			Claim r = rootClaim;
			while (r.getNext() != null) {
				r = r.getNext();
			}
			r.setNext(claim);
		}
	}
	
	public double [] calculateTotalTowerDebt() {
		double [] result = new double[towers];
		int actualTower = 0;
		for (int i = 0; i < apartaments.size(); i++) {
			if (actualTower != Integer.parseInt(apartaments.get(i).getTower())) {
				actualTower = Integer.parseInt(apartaments.get(i).getTower());
			}
			result[actualTower - 1] += apartaments.get(i).getTotalDebt();
		}
		return result;
	}



	//Edit person

	//Podria ser private 

	public void editPerson(Person person,String firstName, String lastName, int phoneNumber, String id){

		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhoneNumber(phoneNumber);
		person.setId(id);


	}


	//Reports

	public void GenerateReport(Apartament apartament){
		try{
            JasperReport report = JasperCompileManager.compileReport("src/reports/test14.jrxml");


            JasperPrint jprint = JasperFillManager.fillReport(report, null, new Invoice(apartament));

          
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
            
        }catch(JRException ex){
            ex.getMessage();
        }
        
	}



}
