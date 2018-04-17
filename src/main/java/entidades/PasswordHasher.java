package entidades;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
	
	//workload es el valor que utiliza para hashear (valido 10-31)
	private static int workload = 16;
	
	//metodo para hashear passwords
	public static String hashPassword(String passwordEnTextoPlano) {
		String salt = BCrypt.gensalt(workload);
		String hashedPassword = BCrypt.hashpw(passwordEnTextoPlano, salt);

		return(hashedPassword);
	}

	//metodo para verificar hash del pw contra texto plano introducido
	public static boolean checkPassword(String textoPlanoIntroducido, String hashPersistido) {
		boolean password_verified = false;

		if(null == hashPersistido || !hashPersistido.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(textoPlanoIntroducido, hashPersistido);

		return(password_verified);
	}

}
