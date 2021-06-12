package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.PasswordInvalidException;
import exceptions.UsernameInvalidException;

public class ResidenceManagementTest {
	
	private ResidenceManagement rm;
	
	public void scenaryOne() {
		rm = new ResidenceManagement(4,450);
	}
	
	public void scenaryTwo() {
		rm = new ResidenceManagement(2,2, 4, 450);
		rm.setAdmin(new Admin("Gabriel", "Sape", 354211211, "2454353"));
	}
	
	public void scenaryThree() {
		// Generates 1 tower with 3 floors and 2 apartments per floor
		rm = new ResidenceManagement(1, 3, 2, 450);
		
	}
	
	public void scenaryFour() {
		rm = new ResidenceManagement(1,2,2,450);
		rm.addServiceStaff("Juan", "García", 46346345, "1016567561", "Doorman");
		rm.addServiceStaff("Gabriel", "Delgado", 46346345, "101345563461", "Doorman");
		rm.addServiceStaff("Carlos", "Ramirez", 54645645, "101345345341", "Doorman");
		rm.addServiceStaff("Valentina", "García", 645646, "101456451", "Doorman");
		
		rm.addServiceStaff("Juan", "García", 46346345, "1016567561", "SERVICE STAFF");
		rm.addServiceStaff("Gabriel", "Delgado", 46346345, "1016567561", "SERVICE STAFF");
		rm.addServiceStaff("Carlos", "Ramirez", 46346345, "1016567561", "SERVICE STAFF");
		rm.addServiceStaff("Valentina", "García", 46346345, "1016567561", "SERVICE STAFF");
		
		rm.addResident(rm.getApartaments().get(0), "Juan", "García", 46346345, "1016567561");
		rm.addResident(rm.getApartaments().get(1), "Gabriel", "Delgado", 46346345, "101345563461");
		rm.addResident(rm.getApartaments().get(2), "Carlos", "Ramirez", 54645645, "101345345341");
		rm.addResident(rm.getApartaments().get(3), "Valentina", "García", 645646, "101456451");
		
		rm.addPet(rm.getApartaments().get(0), "Milán", "DOG");
		rm.addPet(rm.getApartaments().get(1), "Kingy", "CAT");
		rm.addPet(rm.getApartaments().get(2), "Nala", "DOG");
		rm.addPet(rm.getApartaments().get(3), "Mia", "CAT");
		
		rm.addOwner(rm.getApartaments().get(0), "Juan", "García", 46346345, "1016567561", "sape1@gmail.com");
		rm.addOwner(rm.getApartaments().get(1), "Gabriel", "Delgado", 46346345, "101345563461", "sape2@gmail.com");
		rm.addOwner(rm.getApartaments().get(2), "Carlos", "Ramirez", 54645645, "101345345341", "sape3@gmail.com");
		rm.addOwner(rm.getApartaments().get(3), "Valentina", "García", 645646, "101456451", "sape4@gmail.com");
	}

	@Test
	public void testCreateApartments() {
		scenaryOne();
		int towers = 2;
		int floors = 2;
		
		rm.setTowers(towers);
		rm.setFloors(floors);
		
		rm.createApartaments(towers, floors, rm.getApartamentsPerFloor());
		
		assertEquals(16, rm.getApartaments().size());
		for (int i = 0; i < rm.getApartaments().size(); i++) {
			assertNotNull(rm.getApartaments().get(i));
		}
	}
	
	@Test
	public void testLoginAdmin() {
		scenaryTwo();
		
		String username = "Root";
		String password = "1234";
		boolean test = rm.loginAdmin(username, password);
		assertTrue(test);
		
		String usernameFail = "Admin";
		String passwordFail = "5451234";
		boolean test2 = rm.loginAdmin(usernameFail, passwordFail);
		assertTrue(!test2);
		
		boolean test3 = rm.loginAdmin(username, passwordFail);
		assertTrue(!test3);
		
		boolean test4 = rm.loginAdmin(usernameFail, password);
		assertTrue(!test4);
	}
	
	@Test
	public void testLoginApartment() {
		scenaryThree();
		
		String username = "202_1";
		String password = "1234";
		
		boolean loggedIn = false;
		try {
			loggedIn = rm.loginApartament(username, password);
		} catch (UsernameInvalidException | PasswordInvalidException e) {
			e.printStackTrace();
		}
		assertTrue(loggedIn);
		
		/*System.out.println(rm.getApartaments().get(0).toString());
		System.out.println(rm.getApartaments().get(1).toString());
		System.out.println(rm.getApartaments().get(2).toString());
		System.out.println(rm.getApartaments().get(3).toString());*/
		
			
			
	}
	
	@Test
	public void testBinarySearchApartment() {
		scenaryThree();
		String number = "201";
		String username = "201_1";
		String password = "1234";
		Apartament apto = new Apartament(rm.getTowers() + "", number, username, password);
		
		int index = rm.binarySearchApartament(apto);
		
		
		assertEquals(rm.getApartaments().get(index).getNumber(), apto.getNumber());
		assertEquals(rm.getApartaments().get(index).getTower(), apto.getTower());
		assertTrue(rm.getApartaments().get(index).equals(apto));
		
	}
	
	@Test
	public void testGenerateAdministrationDebt() {
		scenaryThree();
		
		LocalDate date = LocalDate.now();
		
		// System.out.println(date);
		
		rm.generateAdministrationDebt(date);
		
		double fee = rm.getAdministrationFee();
		for (int i = 0; i < rm.getApartaments().size(); i++) {			
			double apFee = rm.getApartaments().get(i).getDebt().get(0).getPrice();
			assertEquals(fee, apFee);
			assertTrue(!rm.getApartaments().get(i).getDebt().isEmpty());
		}
	}
	
	@Test
	public void testGenerateDebt() {
		scenaryThree();
		
		LocalDate date = LocalDate.now();
		String desc = "Cuota de administracion";
		double fee = rm.getAdministrationFee();
		
		rm.generateDebt(desc, date, fee, rm.getApartaments().get(2), 1);
		
		double apFee = rm.getApartaments().get(2).getDebt().get(0).getPrice();
		
		assertEquals(fee, apFee);
	}

	@Test
	public void testAddAdmin() {
		scenaryFour();
		String fn = "Juan";
		String ln = "Garcia";
		int pn = 3193445;
		String id = "12343992";
		
		rm.addAdmin(fn, ln, pn, id);
		
		assertEquals(fn, rm.getAdmin().getFirstName());
		assertEquals(ln, rm.getAdmin().getLastName());
		assertEquals(pn, rm.getAdmin().getPhoneNumber());
		assertEquals(id, rm.getAdmin().getId());
	}
	
	@Test
	public void testEditAdmin() {
		testAddAdmin();
		String fn= "Juan";
		String ln= "Garcia";
		int pn=453322;
		String id="427652";

		rm.editAdmin(fn, ln, pn, id);
		
		assertEquals(fn, rm.getAdmin().getFirstName());
		assertEquals(ln, rm.getAdmin().getLastName());
		assertEquals(pn, rm.getAdmin().getPhoneNumber());
		assertEquals(id, rm.getAdmin().getId());
		
	}
	
	@Test
	public void testGenerateInvoicesForAll() {
		scenaryFour();
		LocalDate date = LocalDate.now();
		String description = "Cuota";
		double price = 55000;
		
		rm.generateInvoiceForAll(date, description, price);
		
		boolean result = true;
		for (int i = 0; i < rm.getApartaments().size(); i++) {
			
			if (price != rm.getApartaments().get(i).getTotalDebt()) {
				result = false;
			}
		}
		
		assertTrue(result);
	}
	
	@Test
	public void testBubbleSortDoormen() {
		scenaryFour();
		rm.bubbleSortDoormen();
		boolean result = true;
		for (int i = 0; i < rm.getDoormen().size() - 1; i++) {
			if (rm.getDoormen().get(i).compareTo(rm.getDoormen().get(i)) < 0) {
				result = false;
			}
		}
		
		assertTrue(result);
	}
	
	@Test
	public void testInsertionSortResidents() {
		scenaryFour();
		rm.insertionSortResidents();
		boolean result = true;
		for (int i = 0; i < rm.getResidents().size() - 1; i++) {
			if (rm.getResidents().get(i).compareTo(rm.getResidents().get(i)) < 0) {
				result = false;
			}
		}
		
		assertTrue(result);
	}
	
	@Test
	public void testSelectionSortServiceStaff() {
		scenaryFour();
		rm.selectionSortServiceStaff();
		boolean result = true;
		for (int i = 0; i < rm.getServiceStaff().size() - 1; i++) {
			if (rm.getServiceStaff().get(i).compareTo(rm.getServiceStaff().get(i)) < 0) {
				result = false;
			}
		}
		
		assertTrue(result);
	}
	
	@Test
	public void testBinarySearchPets() {
		
	}
	
	@Test
	public void testSearcgCommonZone() {
		
	}
	
	@Test
	public void testAddReservation() {
		
	}
	
	@Test
	public void testAddClaim() {
		
	}
	
	@Test
	public void testCalculateTotalTowerDebt() {
		
	}
	
	@Test
	public void testSetupCommonZones() {
		scenaryFour();
		ArrayList<CommonZones> arrCz = rm.setupCommonZones();
		boolean result = true;
		for (int i = 0; i < arrCz.size(); i++) {
			if (!arrCz.get(i).getName().equals(rm.getCommonZones().get(i).getName()) || arrCz.get(i).getCapacity() != (rm.getCommonZones().get(i).getCapacity())) {
				result = false;
			}
		}
		assertTrue(result);
	}

	@Test
	public void testAddServiceStaff() {
		scenaryFour();
		ServiceStaff ss = new ServiceStaff("Miguel", "Sape", 316781452, "1006542");
		int size = rm.getServiceStaff().size();
		rm.addServiceStaff("Miguel", "Sape", 316781452, "1006542", "SERVICE STAFF");
		
		assertTrue(rm.getServiceStaff().size() == size + 1);
		assertEquals(ss.getFirstName() + " " + ss.getLastName(),rm.getServiceStaff().get(size).getFirstName() + " " + rm.getServiceStaff().get(size).getLastName());
		
		Doorman dm = new Doorman("Jun", "Sape", 316781452, "1006542");
		size = rm.getDoormen().size();
		rm.addServiceStaff("Jun", "Sape", 316781452, "1006542", "Doorman");
		
		assertTrue(rm.getDoormen().size() == size + 1);
		assertEquals(dm.getFirstName() + " " +dm.getLastName(),rm.getDoormen().get(size).getFirstName() + " " + rm.getDoormen().get(size).getLastName());
	}

	@Test
	public void testDeleteServiceStaff() {
		scenaryFour();
		ServiceStaff ss = rm.getServiceStaff().get(0);
		int size = rm.getServiceStaff().size();
		rm.deleteServiceStaff(ss.getId());
		
		assertTrue(size > rm.getServiceStaff().size());
	}
	
	@Test
	public void testDeleteDoorman() {
		scenaryFour();
		Doorman dm = rm.getDoormen().get(0);
		int size = rm.getDoormen().size();
		rm.deleteDoorman(dm.getId());
		
		assertTrue(size > rm.getDoormen().size());
	}
	
	@Test
	public void testAddResident() {
		scenaryFour();
		Resident r = new Resident("TestOne", "TestTwo", 54534, "1042342");
		int sizeRm = rm.getResidents().size();
		int sizeAp = rm.getApartaments().get(0).getResidents().size();
		rm.addResident(rm.getApartaments().get(0), "TestOne", "TestTwo", 54534, "1042342");
		
		assertTrue(rm.getResidents().size() > sizeRm);
		assertTrue(rm.getApartaments().get(0).getResidents().size() > sizeAp);
		
		boolean result = false;
		Resident apR = rm.getApartaments().get(0).getResidents().get(sizeAp);
		if ((r.getFirstName() + " " + r.getLastName()).equals(apR.getFirstName() + " " + apR.getLastName())) {
			result = true;
		}
		
		assertTrue(result);
	}

	@Test
	public void testDeleteResident() {
		scenaryFour();
		Resident r = rm.getApartaments().get(0).getResidents().get(0);
		int sizeAp = rm.getApartaments().get(0).getResidents().size();
		int sizeRm = rm.getResidents().size();
		
		rm.deleteResident(rm.getApartaments().get(0), r.getId());
		
		assertTrue(sizeAp > rm.getApartaments().get(0).getResidents().size());
		assertTrue(sizeRm > rm.getResidents().size());
	}
	
	@Test
	public void testAddPet() {
		scenaryFour();
		Pet p = new Pet("Mickey", "CAT");
		int sizeRm = rm.getPets().size();
		int sizeAp = rm.getApartaments().get(0).getPets().size();
		rm.addPet(rm.getApartaments().get(0), "Mickey", "CAT");
		
		assertTrue(rm.getApartaments().get(0).getPets().size() > sizeAp);
		assertTrue(rm.getPets().size() > sizeRm);
		
		boolean result = false;
		Pet apP = rm.getApartaments().get(0).getPets().get(sizeAp);
		if (p.getName().equals(apP.getName()) && p.getType().equals(apP.getType())) result = true;
		assertTrue(result);
	}

	@Test
	public void testDeletePet() {
		scenaryFour();
		Pet p = rm.getApartaments().get(0).getPets().get(0);
		int sizeAp = rm.getApartaments().get(0).getPets().size();
		int sizeRm = rm.getPets().size();
		
		rm.deletePet(rm.getApartaments().get(0), p.getName(), p.getType() + "");
		assertTrue(sizeAp > rm.getApartaments().get(0).getPets().size());
		assertTrue(sizeRm > rm.getPets().size());
	}

	@Test
	public void testAddVehicle() {
		scenaryFour();
		Vehicle v = new Car("Ayo123");
		int sizeRm = rm.getVehicles().size();
		int sizeAp = rm.getApartaments().get(0).getCars().size();
		rm.addVehicle(rm.getApartaments().get(0), "Ayo123", "CAR");
		
		assertTrue(rm.getApartaments().get(0).getCars().size() > sizeAp);
		assertTrue(rm.getVehicles().size() > sizeRm);
		
		boolean result = false;
		Vehicle apV = rm.getApartaments().get(0).getCars().get(sizeAp);
		if (v.getLicensePlate().equals(apV.getLicensePlate()) && v.getType().equals(apV.getType())) result = true;
		assertTrue(result);
	}

	@Test
	public void testDeleteVehicle() {
		testAddVehicle();
		Vehicle p = rm.getApartaments().get(0).getCars().get(0);
		int sizeAp = rm.getApartaments().get(0).getCars().size();
		int sizeRm = rm.getVehicles().size();
		
		rm.deleteVehicle(rm.getApartaments().get(0), p.getLicensePlate());
		assertTrue(sizeAp > rm.getApartaments().get(0).getCars().size());
		assertTrue(sizeRm > rm.getVehicles().size());
	}
	
	@Test
	public void testAddOwner() {
		scenaryFour();
		Owner o = new Owner("TestOne", "TestTwo", 54534, "1042342", "Sape@gmail.com");
		int sizeRm = rm.getOwners().size();
		rm.addOwner(rm.getApartaments().get(0), "TestOne", "TestTwo", 54534, "1042342", "Sape@gmail.com");
		
		assertTrue(rm.getResidents().size() > sizeRm);
		
		boolean result = false;
		Owner apO = rm.getApartaments().get(0).getOwner();
		if ((o.getFirstName() + " " + o.getLastName()).equals(apO.getFirstName() + " " + apO.getLastName())) {
			result = true;
		}
		
		assertTrue(result);
	}

	@Test
	public void testEditOwner() {
		scenaryFour();
		Owner on = rm.getApartaments().get(0).getOwner();
		
		rm.editOwner(rm.getApartaments().get(0), on.getFirstName(), on.getLastName(), 345343, "10043343", "NewEmail@gmail.com");
		
		Owner on2 = rm.getApartaments().get(0).getOwner();
		assertEquals(on.getFirstName(), on2.getFirstName());
		assertEquals(on.getLastName(), on2.getLastName());
		assertTrue(!on.getMail().equals(on2.getMail()));
	
	}

	@Test 
	public void testDeleteOwner() {
		scenaryFour();
		Owner on = rm.getApartaments().get(0).getOwner();
		int size = rm.getOwners().size();
		assertTrue(on != null);
		rm.deleteOwner(rm.getApartaments().get(0), on.getId());
		assertTrue(size > rm.getOwners().size());
		assertTrue(rm.getApartaments().get(0).getOwner() == null);
	}

	@Test
	public void testEditPerson() {
		scenaryFour();
		Resident r = rm.getResidents().get(0);
		String firstName = "Hey";
		String lastName = "There";
		String id = "42342523";
		int phoneNumber = 434342;
		
		assertTrue(!r.getFirstName().equals(firstName));
		assertTrue(!r.getLastName().equals(lastName));
		assertTrue(!r.getId().equals(id));
		assertTrue(r.getPhoneNumber() != phoneNumber);
		
		rm.editPerson(r, firstName, lastName, phoneNumber, id);
		assertEquals(r.getFirstName(), firstName);
		assertEquals(r.getLastName(), lastName);
		assertEquals(r.getPhoneNumber(), phoneNumber);
		assertEquals(r.getId(), id);
	}
}
