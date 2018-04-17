package fixtures;

import java.util.ArrayList;
import java.util.List;

import condicionesImpuestasPorElUser.CondicionLongevidad;
import condicionesMetodologiasTests.FixtureEmpresa;
import entidades.Condicion;
import entidades.Empresa;
import entidades.Metodologia;

public class FixtureMetodologia {
	public Metodologia metodologia = new Metodologia("prueba");
	public Condicion condicion1 = new CondicionLongevidad(2017);
	public FixtureEmpresa fixtureEmp = new FixtureEmpresa();
	public Empresa empresa;
	public List<Metodologia> metodologiasDeLaBaseDeDatos = new ArrayList<Metodologia>();
}
