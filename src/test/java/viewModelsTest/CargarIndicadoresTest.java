package viewModelsTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import excepciones.ArchivoException;
import parser.ParserIndicadores;
import repositorios.RepositorioIndicadores;
import viewModels.CargarIndicador;

public class CargarIndicadoresTest {

	
	@Test
	public void elArchivoDeIndicadoresSeCargo() throws NullPointerException, ArchivoException{
		//se toma un indicador cualquiera para chequear que se cargo al
		//repositorio de indicadores
		CargarIndicador testCargarIndicador = new CargarIndicador();
		testCargarIndicador.setNombreArchivo("indicadores.json");
		testCargarIndicador.cargarArchivoDeIndicadores();
		assertEquals("666", RepositorioIndicadores.indicadores.get(9).getFormula());
	}
}
