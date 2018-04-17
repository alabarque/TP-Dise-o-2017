package lecturaDeArchivoDeCuentas;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import basedatos.EmpresaAdapter;
import entidades.Empresa;

public class LeerArchivoCuentasTest {
	List<Empresa> listaEmpresas = new ArrayList<Empresa>();
	
    @Before
    public void setUp() throws Exception {

    	EmpresaAdapter adapter = new EmpresaAdapter();
		listaEmpresas = adapter.leerArchivoEmpresas(new File("empresas.json"));
    }

	@Test
	public void testVerificarCantidadEmpresasEnArchivo() {
		assertEquals(2, listaEmpresas.size());
	}
	
	@Test
	public void testElPrimeroEsFacebook() {
		assertEquals("Facebook", listaEmpresas.get(0).getNombre());
	}
	
	@Test
	public void testFacebookTiene2Periodos() {
		assertEquals(2, listaEmpresas.get(0).cantPeriodos());
	}
	
	@Test
	public void testFacebookTiene6CuentasEnPeriodo2017() {
		assertEquals(6, listaEmpresas.get(0).getPeriodos().get(1).cantCuentas());
	}
	
	@Test
	public void testCotoTiene4CuentasEnPeriodo2017(){
		assertEquals(4, listaEmpresas.get(1).getPeriodos().get(0).cantCuentas());
	}
	
	@Test
	public void cotoTienePeriodo2017(){
		assertEquals(2017, listaEmpresas.get(1).getPeriodos().get(0).getPeriodo(), 0);
		
	}
	
	@Test
	public void testElSegundoEsCoto() {
		assertEquals("COTO", listaEmpresas.get(1).getNombre());
	}
	
	@Test
	public void testVerificarEBITDACOTO() {
		assertEquals(10.0, listaEmpresas.get(1).getPeriodos().get(0).getCuentas().get(0).getValor(), 0);
	}
	
	

}