package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ResidenceManagementTest {
	
	private ResidenceManagement rm;
	
	public ResidenceManagementTest() {
		rm = new ResidenceManagement(3, 8, 10, 450);
	}
	
	public void scenaryOne( ) {
		
	}
	
	public void scenaryTwo( ) {
			rm.setAdmin(new Admin("Gabriel", "Sape", 354211211, "2454353"));
	}
	
	public void scenaryThree( ) {
		
	}
	
	public void scenaryFour( ) {
		
	}

	
	@Test
	public void testCreateApartments() {
		scenaryOne();
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
	}
	
	@Test
	public void testBinarySearchApartment() {
		scenaryThree();
	}
	
	@Test
	public void testGenerateAdministrationDebt() {
		scenaryThree();
	}
	
	@Test
	public void testGenerateDebt() {
		scenaryFour();
	}
}
