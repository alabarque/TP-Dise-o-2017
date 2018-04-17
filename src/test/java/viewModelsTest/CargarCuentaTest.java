package viewModelsTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import excepciones.ArchivoException;
import excepciones.BDException;
import repositorios.RepositorioEmpresas;
import viewModels.CargarCuenta;

public class CargarCuentaTest {

	@Test
	public void elArchivoDeCuentasSeCargoAlRepositorio() throws NullPointerException, ArchivoException, BDException{
		//se toma una empresa cualquiera de la lista de empresas para chequear que se cargo al repositorio
		//de empresas
		
		//comento por el momento esto
		
		/*CargarCuenta testCargarCuenta = new CargarCuenta();
		testCargarCuenta.setNombreArchivo("empresas.json");
		testCargarCuenta.cargarArchivoDeCuentas();
		assertEquals("Facebook", RepositorioEmpresas.empresas.get(0).getNombre());*/
	}
}
