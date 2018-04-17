package condicionesMetodologiasTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entidades.Empresa;
import entidades.Periodo;
import excepciones.ArchivoException;
import excepciones.IndicadorException;
import operacionDeCondiciones.ObtenerValorIndicador;
import repositorios.RepositorioEmpresas;
import repositorios.RepositorioIndicadores;

public class ObtenerValorIndicadorTest {
	FixtureEmpresa fixtureEmpresa = new FixtureEmpresa();
	ObtenerValorIndicador valuador = new ObtenerValorIndicador();
	FixtureIndicadores fixtureIndicadores = new FixtureIndicadores();
			
	@Before
	public void setUp() throws NullPointerException, ArchivoException{
	 RepositorioIndicadores.agregarIndicador(fixtureIndicadores.fixtureIndicadorPrueba());
	}
	
	 @Test
	 public void periodoFacebookUnoDa197Con02Test() throws NullPointerException, ArchivoException{
		 assertEquals(197.02, valuador.obtenerValor(fixtureEmpresa.fixtureEmpresa1().getPeriodos().get(0), "IngresoNetoEnOperacionesDiscontinuas"),0);
	 }
	 
}
