package basedatos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.util.DefaultPrettyPrinter;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import entidades.Indicador;
import entidades.Metodologia;
import excepciones.ArchivoException;
import excepciones.BDException;

public class GrabarMetodologia {

	public static void GrabarMetodologia(Metodologia metodologia)throws ArchivoException
	{

		MetodologiaAdapter metodologiaAdaptar = new MetodologiaAdapter();

		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

		List<Metodologia> listaMetodologias = new ArrayList<Metodologia>();
		try {
			listaMetodologias = (metodologiaAdaptar.leerArchivoMetodologia(new File("metodologias_personales.json")));
		} catch (ArchivoException exception) {
			listaMetodologias = new ArrayList<Metodologia>();
		}

		listaMetodologias.add(metodologia);
		try {
			writer.writeValue(new File("metodologias_personales.json"), listaMetodologias);
		} catch (Exception e) {
			throw new ArchivoException("Error de Formato al Escribir Archivo");
		

}
			}
	
	
	//base de datos/hibernate
	public static void persistir(Metodologia metodologia) throws BDException{
		EntityManager em = PerThreadEntityManagers.getEntityManager();
	    EntityTransaction transaction = em.getTransaction();
	    try {
	    	
	        transaction.begin();
	        
	        em.persist(metodologia);
	        
	        transaction.commit();
	        
	    }catch (Exception e) {
	    	
        transaction.rollback();
        throw new BDException("Error al Insertar la Metodologia");
        
	    } finally {
        em.close();
	    }
	}
}
