package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

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
		
		int index = rm.binarySearchApartament(rm.getApartaments(), apto);
		
		
		assertEquals(rm.getApartaments().get(index).getNumber(), apto.getNumber());
		assertEquals(rm.getApartaments().get(index).getTower(), apto.getTower());
		assertTrue(rm.getApartaments().get(index).equals(apto));
		
	}
	
	@Test
	public void testGenerateAdministrationDebt() {
		scenaryThree();
		
		Date date = new Date();
		
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
		
		Date date = new Date();
		String desc = "Cuota de administracion";
		double fee = rm.getAdministrationFee();
		
		rm.generateDebt(desc, date, fee, rm.getApartaments().get(2));
		
		double apFee = rm.getApartaments().get(2).getDebt().get(0).getPrice();
		
		assertEquals(fee, apFee);
	}
}
