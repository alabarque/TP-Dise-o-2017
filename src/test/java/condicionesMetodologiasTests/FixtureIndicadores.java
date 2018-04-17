package condicionesMetodologiasTests;

import java.util.ArrayList;
import java.util.List;

import entidades.Cuenta;
import entidades.Indicador;
import entidades.Periodo;
import excepciones.ArchivoException;
import viewModels.CargarIndicador;

public class FixtureIndicadores {

	public CargarIndicador testCargarIndicador = new CargarIndicador();
	public List<Periodo> listaPeriodosEmpresa1 = new ArrayList<Periodo>();
	public List<Periodo> listaPeriodosEmpresa2 = new ArrayList<Periodo>();
	Periodo periodo = new Periodo(2015);
	Cuenta cuenta = new Cuenta("IngresoNetoEnOperacionesDiscontinuas", 197.02);
	Cuenta cuenta2 = new Cuenta("IngresoNetoEnOperacionesContinuas", 71.05);
	Periodo periodo2 = new Periodo(2017);
	Cuenta cuenta3 = new Cuenta("IngresoNetoEnOperacionesDiscontinuas", 176.22);
	Cuenta cuenta4 = new Cuenta("IngresoNetoEnOperacionesContinuas", 107.23);
	Indicador indicadorPrueba = new Indicador();
	
	
	private void cargarCuentas() throws NullPointerException, ArchivoException{
		periodo.getCuentas().clear();
		periodo2.getCuentas().clear();
		testCargarIndicador.setNombreArchivo("indicadores.json");
		testCargarIndicador.cargarArchivoDeIndicadores();
		periodo.agregarCuentas(cuenta);
		periodo.agregarCuentas(cuenta2);
		periodo2.agregarCuentas(cuenta3);
		periodo2.agregarCuentas(cuenta4);
	}
	
	public FixtureIndicadores fixtureIndicadoresEmpresa1() throws NullPointerException, ArchivoException{
		this.cargarCuentas();
		listaPeriodosEmpresa1.clear();
		listaPeriodosEmpresa1.add(periodo);
		listaPeriodosEmpresa1.add(periodo2);
		return this;
	}
	
	public FixtureIndicadores fixtureIndicadoresEmpresa2() throws NullPointerException, ArchivoException{
		this.cargarCuentas();
		listaPeriodosEmpresa2.clear();
		listaPeriodosEmpresa2.add(periodo2);
		listaPeriodosEmpresa2.add(periodo);
		return this;
	}
	
	public Indicador fixtureIndicadorPrueba(){
		indicadorPrueba.setNombre("IngresoNetoEnOperacionesDiscontinuas");
		indicadorPrueba.setFormula("197.02");
		return indicadorPrueba;
	}
}
