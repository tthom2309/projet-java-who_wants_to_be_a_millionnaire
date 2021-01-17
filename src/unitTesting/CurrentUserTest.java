package unitTesting;

import junit.framework.TestCase;

import model.CurrentUser;
import model.User;
import test.Explorateur;

public class CurrentUserTest extends TestCase {

	


	protected void setUp() throws Exception {
		User u1=new User("Altares Menendez", "Valentin", false, "test@test.test", "Valentin Forever", "helha", "10/05/2016");
		CurrentUser.getInstance().setUser(u1);
	}


	protected void tearDown() throws Exception {
	}


	public void testGetUser() {
		User tmp=new User("Altares Menendez", "Valentin", false, "test@test.test", "Valentin Forever", "helha", "10/05/2016");
		assertEquals(tmp.toString(),CurrentUser.getInstance().getUser().toString());
	}


	public void testSetUser() {
		User tmp=new User("Test", "test", false, "test1@test.test", "Test", "helha", "10-05-2016");
		CurrentUser.getInstance().setUser(tmp);
		assertEquals(CurrentUser.getInstance().getUser(),tmp);
	}

	
	public void testIsAdmin() {
		assertFalse(CurrentUser.getInstance().isAdmin()==true);
	}

	
	public void testSetAdmin() {
		CurrentUser.getInstance().setAdmin(true);
		assertTrue(CurrentUser.getInstance().isAdmin()==true);
		
		CurrentUser.getInstance().setAdmin(false);
		assertTrue(CurrentUser.getInstance().isAdmin()==false);
	}

}
