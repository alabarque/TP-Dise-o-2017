package condicionesMetodologiasTests;

import java.util.ArrayList;
import java.util.List;

import entidades.Empresa;
import excepciones.ArchivoException;

public class FixtureEmpresa {
	
	
	public Empresa empresa = new Empresa("feisbuc");
	public Empresa empresa2 = new Empresa("fakebuk");
	public int cantidadAnios = 5;
	List<Empresa> listaEmpresas = new ArrayList<Empresa>();
	
	public Empresa fixtureEmpresa1() throws NullPointerException, ArchivoException{
		FixtureIndicadores fixtureInd = new FixtureIndicadores();
		empresa.setPeriodos(fixtureInd.fixtureIndicadoresEmpresa1().listaPeriodosEmpresa1);
	return empresa;
	}
	
	public Empresa fixtureEmpresa2() throws NullPointerException, ArchivoException{
		FixtureIndicadores fixtureInd = new FixtureIndicadores();
		empresa2.setPeriodos(fixtureInd.fixtureIndicadoresEmpresa2().listaPeriodosEmpresa2);
		return empresa2;
	}
	
	public List<Empresa> listaEmpresas() throws NullPointerException, ArchivoException{
		listaEmpresas.add(this.fixtureEmpresa1());
		listaEmpresas.add(this.fixtureEmpresa2());
		return listaEmpresas;
	}
}
