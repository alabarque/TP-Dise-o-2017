package lecturaDeArchivo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import basedatos.EmpresaAdapter;
import excepciones.ArchivoException;

public class LeerArchivoVacioTest {
	List<?> lista = new ArrayList<>();


	@Test(expected=ArchivoException.class)
	public void elAdapterTiraExcepcionDeEntradaSalida() throws ArchivoException {
		EmpresaAdapter adapter = new EmpresaAdapter();
    	lista = adapter.leerArchivoEmpresas(new File("archivoVacio.json"));
	}
}
