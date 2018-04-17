package lecturaDeArchivoDeIndicadores;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entidades.Cuenta;
import entidades.Indicador;
import entidades.Periodo;
import excepciones.ArchivoException;
import excepciones.IndicadorException;
import repositorios.RepositorioIndicadores;
import viewModels.CargarIndicador;

public class EvaluarIndicadorTest {

	CargarIndicador testCargarIndicador = new CargarIndicador();
	Periodo periodo = new Periodo(2015);
	Cuenta cuenta = new Cuenta("IngresoNetoEnOperacionesDiscontinuas", 197.02);
	Cuenta cuenta2 = new Cuenta("IngresoNetoEnOperacionesContinuas", 71.05);
	
	
	
	@Before
	public void setUp() throws NullPointerException, ArchivoException{
	testCargarIndicador.setNombreArchivo("indicadores.json");
	testCargarIndicador.cargarArchivoDeIndicadores();
	periodo.agregarCuentas(cuenta);
	periodo.agregarCuentas(cuenta2);
	
	}
	
	@Test (expected = IndicadorException.class) 
	public void elIndicadorNoExisteTest() throws IndicadorException{
		RepositorioIndicadores.evaluarIndicador("", periodo);
	}

	@Test public void elIndicadorIngresoNetoDevuelve268con07() throws IndicadorException{
		assertEquals(268.07, RepositorioIndicadores.evaluarIndicador("IngresoNeto", periodo), 0);
	}
	
	@Test
	public void elIndicadorTHENUMBEROFTHEBEASTDevuelve666() throws IndicadorException{
		assertEquals(666, RepositorioIndicadores.evaluarIndicador("THENUMBEROFTHEBEAST", periodo), 0 );
	}
	
	
	@Test
	public void elIndicadorINDICADOR1Devuelve200() throws IndicadorException{
		
		assertEquals(200, RepositorioIndicadores.evaluarIndicador("INDCADOR1", periodo), 0);
	}
	
	// Test de Indicador compuesto de otro indicador + un valor
	@Test
	public void elIndicadorINDICADOR2Devuelve400() throws IndicadorException{ 
		
		assertEquals(400, RepositorioIndicadores.evaluarIndicador("INDCADOR2", periodo), 0);
	}
	
	@Test (expected = IndicadorException.class) 
	public void elIndicadorINDICADOR3Devuelve100() throws IndicadorException{
		
		assertEquals(100 , RepositorioIndicadores.evaluarIndicador("INDCADOR3", periodo), 0);
	}
	
	// Test de un indicador compuesto de otros indicadores
	@Test (expected = IndicadorException.class) 
	public void elIndicador_IndicadorconotroindicadorDevuelve100() throws IndicadorException{
		
		assertEquals(10000 , RepositorioIndicadores.evaluarIndicador("Indicadorconotroindicador", periodo), 0);
	}
	
	
}
