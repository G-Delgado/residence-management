package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import exceptions.PasswordInvalidException;
import exceptions.UsernameInvalidException;

public class ResidenceManagementTest {
	
	private ResidenceManagement rm;
	
	public void scenaryOne( ) {
		rm = new ResidenceManagement(2, 2, 4,450);
	}
	
	public void scenaryTwo( ) {
		rm = new ResidenceManagement(2,2, 4, 450);
		rm.setAdmin(new Admin("Gabriel", "Sape", 354211211, "2454353"));
	}
	
	public void scenaryThree( ) {
		// Generates 1 tower with 3 floors and 2 apartments per floor
		rm = new ResidenceManagement(1, 3, 2, 450);
		
	}

	
	@Test
	public void testCreateApartments() {
		scenaryOne();
		
		assertEquals(16, rm.getApartaments().size());
		for (int i = 0; i < rm.getApartaments().size(); i++) {
			assertNotNull(rm.getApartaments().get(i));
		}
	}
	
	@Test
	public void testLoginAdmin() {
		scenaryTwo();
		
		boolean test = rm.loginAdmin("Root", "1234");
		assertTrue(test);
		
		boolean test2 = rm.loginAdmin("Admin", "5451234");
		assertTrue(!test2);
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
		
		Apartament apto = new Apartament(rm.getTowers() + "", "201", "201_1", "1234");
		
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
		double apFee = rm.getApartaments().get(2).getDebt().get(0).getPrice();
		
		assertEquals(fee, apFee);
		assertTrue(!rm.getApartaments().get(1).getDebt().isEmpty());
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
