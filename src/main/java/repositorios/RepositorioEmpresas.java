package repositorios;

import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import entidades.Empresa;
import entidades.Periodo;

public class RepositorioEmpresas implements WithGlobalEntityManager {

	public static List<Empresa> empresas = new ArrayList<Empresa>();
	public static RepositorioEmpresas instancia = new RepositorioEmpresas();

	public RepositorioEmpresas(List<Empresa> empresas) {
		super();
		RepositorioEmpresas.empresas = empresas;
	}
	
	public RepositorioEmpresas(){}
	
	public static boolean tieneElementos() {
		return (empresas.size() > 0);
	}

	public void listaEmpresas() {
		empresas = entityManager().createQuery("from Empresa",Empresa.class).getResultList();
	}
	
	public static List<Empresa> getEmpresas()
	{
		return empresas;
	}

	public static Periodo obtenerPeriodo(String empresa, String anio) {
		Empresa empresaBuscada = obtenerEmpresa(empresa);
		return obtenerPeriodo(anio, empresaBuscada);
	}

	private static Periodo obtenerPeriodo(String anio, Empresa empresaBuscada) {
		return (empresaBuscada.getPeriodos().stream().filter(periodo -> periodo.getPeriodo().equals( Integer.parseInt(anio))).findFirst()).get();
	}

	public static Empresa obtenerEmpresa(String empresa) {
		return empresas.stream().filter(unaEmpresa -> unaEmpresa.getNombre().equals(empresa)).findFirst().get();
	}
}
