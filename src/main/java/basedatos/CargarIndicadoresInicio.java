package basedatos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import entidades.Indicador;
import excepciones.BDException;
import repositorios.RepositorioIndicadores;

public class CargarIndicadoresInicio {


    static EntityManager em = PerThreadEntityManagers.getEntityManager();
    
    public static void CargarIndicadoresInicio() throws BDException{
    	
    	try {
			RepositorioIndicadores.agregarIndicadores(obtenerIndicadores());
			em.close();
    	}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new BDException("error al leer los indicadores");
		}
    }
    
    private static List<Indicador> obtenerIndicadores(){
		return em.createQuery("from Indicador").getResultList();
	}
}
