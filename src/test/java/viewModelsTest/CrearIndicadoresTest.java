package viewModelsTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import viewModels.CrearIndicadores;

public class CrearIndicadoresTest {

		CrearIndicadores testCrearIndicadores = new CrearIndicadores();
		@Before
		public void setUp(){
		testCrearIndicadores.setNombre("indicadorDDS");
		testCrearIndicadores.setFormula("1");
		}
		
	@Test
	public void seCargoElNombreDelIndicador(){
		assertEquals("indicadorDDS", testCrearIndicadores.crearIndicador().getNombre());
	}
	
	@Test
	public void seCargoLaFormulaDelIndicador(){
		assertEquals("1", testCrearIndicadores.crearIndicador().getFormula());
	}
}
