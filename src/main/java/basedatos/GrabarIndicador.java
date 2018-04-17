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
import excepciones.ArchivoException;
import excepciones.BDException;

public class GrabarIndicador {

	public static void grabarIndicador(Indicador indicador)
			throws ArchivoException {

		IndicadorAdapter indicadorAdapter = new IndicadorAdapter();

		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

		List<Indicador> listaIndicadores = new ArrayList<Indicador>();
		try {
			listaIndicadores = (indicadorAdapter.leerArchivoIndicador(new File("indicadores_personales.json")));
		} catch (ArchivoException exception) {
			listaIndicadores = new ArrayList<Indicador>();
		}

		listaIndicadores.add(indicador);
		try {
			writer.writeValue(new File("indicadores_personales.json"), listaIndicadores);
		} catch (Exception e) {
			throw new ArchivoException("Error de Formato al Escribir Archivo");
		}
	}
	
	public static void GrabarIndicador(Indicador indicador) throws BDException{
		EntityManager em = PerThreadEntityManagers.getEntityManager();
	    EntityTransaction transaction = em.getTransaction();
	    try {
	    	
	        transaction.begin();
	        
	        em.persist(indicador);
	        
	        transaction.commit();
	        
	    }catch (Exception e) {
	    	
        transaction.rollback();
        throw new BDException("Error al Insertar el Indicador");
        
	    } finally {
        em.close();
	    }
	}

}
