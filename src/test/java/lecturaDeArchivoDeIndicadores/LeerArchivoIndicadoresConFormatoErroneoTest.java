package lecturaDeArchivoDeIndicadores;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import basedatos.IndicadorAdapter;
import entidades.Indicador;
import excepciones.ArchivoException;

public class LeerArchivoIndicadoresConFormatoErroneoTest {
	List<Indicador> listaIndicadores = new ArrayList<Indicador>();

	@Test(expected = ArchivoException.class)
	public void elAdapterTiraExcepcionDeParseoJson() throws ArchivoException {
		IndicadorAdapter adapter = new IndicadorAdapter();
		listaIndicadores = (adapter.leerArchivoIndicador(new File("archivoConMalFormato.json")));
	}
}
