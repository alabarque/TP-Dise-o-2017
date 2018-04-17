package lecturaDeArchivoDeCuentas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import basedatos.EmpresaAdapter;
import entidades.Empresa;
import excepciones.ArchivoException;

public class LeerArchivoCuentasConFormatoErroneoTest {
	List<Empresa> listaEmpresas = new ArrayList<Empresa>();


	@Test(expected=ArchivoException.class)
	public void elAdapterTiraExcepcionDeParseoJson() throws ArchivoException  {
		EmpresaAdapter adapter = new EmpresaAdapter();
    	listaEmpresas = adapter.leerArchivoEmpresas(new File("archivoConMalFormato.json"));
	}
}
