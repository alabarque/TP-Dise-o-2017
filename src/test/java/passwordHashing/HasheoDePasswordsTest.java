package passwordHashing;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import entidades.PasswordHasher;

public class HasheoDePasswordsTest {
	
	String pass = "a1b2c3";
	PasswordHasher passhasher = null;
	String hashedpass = passhasher.hashPassword(pass);
	
	@Test
	public void a1b2c3DaVerdaderoTest(){	
		assert(passhasher.checkPassword("a1b2c3", hashedpass));
		
	}
	
	@Test
	public void abc123DaFalsoTest(){
		assertFalse(passhasher.checkPassword("abc123", hashedpass));
	}
	
}
