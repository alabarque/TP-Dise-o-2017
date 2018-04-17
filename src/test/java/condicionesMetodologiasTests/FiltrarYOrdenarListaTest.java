package condicionesMetodologiasTests;

import static org.junit.Assert.*;

import org.junit.Test;

import excepciones.ArchivoException;
import excepciones.TipoDeOrdenIncorrectoException;
import operacionDeCondiciones.FiltrarYOrdernarLista;
import operacionDeCondiciones.Orden;

public class FiltrarYOrdenarListaTest {

	
	FiltrarYOrdernarLista filtradorYOrdenador = new FiltrarYOrdernarLista();
	FixtureEmpresa fixtureEmp = new FixtureEmpresa();
		
	@Test
	public void unIndicadorEnLosUltimos5aniosAntesDel2016() throws NullPointerException, TipoDeOrdenIncorrectoException, ArchivoException{
		assertEquals(1, filtradorYOrdenador.ejecutar(fixtureEmp.fixtureEmpresa1(), 2016, fixtureEmp.cantidadAnios, Orden.CRECIENTE).size());
	}
	
	@Test
	public void ceroIndicadoresEnLosUltimos5aniosAntesDel2014() throws NullPointerException, TipoDeOrdenIncorrectoException, ArchivoException{
		assertEquals(0, filtradorYOrdenador.ejecutar(fixtureEmp.fixtureEmpresa1(), 2014, fixtureEmp.cantidadAnios, Orden.CRECIENTE).size());
	}
	
	@Test
	public void ceroIndicadorEnLosUltimos5aniosAntesDel2030() throws NullPointerException, TipoDeOrdenIncorrectoException, ArchivoException{
		assertEquals(0, filtradorYOrdenador.ejecutar(fixtureEmp.fixtureEmpresa1(), 2030, fixtureEmp.cantidadAnios, Orden.CRECIENTE).size());
	}
	
	@Test
	public void dosIndicadoresEnLosUltimos5aniosAntesDel2017() throws NullPointerException, TipoDeOrdenIncorrectoException, ArchivoException{
		assertEquals(2, filtradorYOrdenador.ejecutar(fixtureEmp.fixtureEmpresa1(), 2017, fixtureEmp.cantidadAnios, Orden.CRECIENTE).size());
	}
}
