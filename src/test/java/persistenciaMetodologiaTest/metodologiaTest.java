package persistenciaMetodologiaTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import basedatos.GrabarMetodologia;
import condicionesImpuestasPorElUser.CondicionLongevidad;
import entidades.Condicion;
import entidades.Empresa;
import entidades.Metodologia;
import excepciones.ArchivoException;
import excepciones.BDException;
import fixtures.FixtureMetodologia;

public class metodologiaTest {
	
	//fixturemetodologia tiene seteado el anio de la condicion de longevidad en 2017
	FixtureMetodologia fixtureMetodologia = new FixtureMetodologia();
	EntityManager em = PerThreadEntityManagers.getEntityManager();
	EntityTransaction tx = em.getTransaction();

	@Before
	public void setUp() throws BDException, NullPointerException, ArchivoException{
		fixtureMetodologia.metodologia.agregarCondicion(fixtureMetodologia.condicion1);
		fixtureMetodologia.empresa = fixtureMetodologia.fixtureEmp.fixtureEmpresa1();
		tx.begin();
		em.persist(fixtureMetodologia.metodologia);
	}
	
	@Test
	public void laMetodologiaSePersistio(){
		fixtureMetodologia.metodologiasDeLaBaseDeDatos = em.createQuery("from Metodologia").getResultList();
		assertEquals(1, fixtureMetodologia.metodologiasDeLaBaseDeDatos.get(0).getCondiciones().size());
	}
	
	
	//el 0000 es para cumplir la interfaz
	@Test
	public void facebookPasaLaCondicionPara2017(){
		fixtureMetodologia.metodologiasDeLaBaseDeDatos = em.createQuery("from Metodologia").getResultList();
		assertTrue(fixtureMetodologia.metodologiasDeLaBaseDeDatos.get(0).evaluarEmpresa(0000,fixtureMetodologia.empresa).stream().allMatch(resultado -> resultado.getResultado()));
	}
	
	@After
	public void finalizar(){
		tx.rollback();
	}

}