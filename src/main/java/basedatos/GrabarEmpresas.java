package basedatos;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import entidades.Empresa;
import excepciones.ArchivoException;
import excepciones.BDException;

public class GrabarEmpresas {
	
	public static void grabarEmpresas(File archivo) throws ArchivoException, BDException {
		EmpresaAdapter empresaAdapter = new EmpresaAdapter();
		List<Empresa> listaEmpresas = empresaAdapter.leerArchivoEmpresas(archivo);
		
		
	    EntityManager em = PerThreadEntityManagers.getEntityManager();
	    EntityTransaction transaction = em.getTransaction();
	    try {
	        transaction.begin();
	        
			listaEmpresas.stream().forEach(empresa->em.persist(empresa));

	        transaction.commit();
	    } catch (Exception e) {
	        transaction.rollback();
	        throw new BDException("Error al Insertar Empresas");
	    } finally {
	        em.close();
	    }
		
	}

}
