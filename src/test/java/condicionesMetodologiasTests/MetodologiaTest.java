package condicionesMetodologiasTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import condicionesImpuestasPorElUser.CondicionLongevidad;
import entidades.Condicion;
import entidades.Metodologia;

public class MetodologiaTest {

	Metodologia metodologia = new Metodologia("hola ke ase");
	
	@Before
	public void setUp(){
		metodologia.agregarCondicion(new CondicionLongevidad());
	}
	
	@Test
	public void tieneUnaCondicion(){
		assertEquals(1, metodologia.getCondiciones().size());
	}
	
	@Test
	public void tieneElNombreDeLoQueDiceUnaYama(){
		assertEquals("hola ke ase", metodologia.getNombre());
	}
}
