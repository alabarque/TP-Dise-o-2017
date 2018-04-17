package basedatos;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import entidades.Cuenta;
import entidades.Indicador;
import entidades.Usuario;
import excepciones.BDException;

public class CrearUsuario {

	public static void GrabarUsuario(Usuario usuario) throws BDException{
		EntityManager em = PerThreadEntityManagers.getEntityManager();
	    EntityTransaction transaction = em.getTransaction();
	    try {
	    	
	        transaction.begin();
	        
	        em.persist(usuario);
	        
	        transaction.commit();
	        
	    }catch (Exception e) {
	    	
        transaction.rollback();
        throw new BDException("Error al Insertar el usuario");
        
	    } finally {
        em.close();
	    }
	}
	
}
