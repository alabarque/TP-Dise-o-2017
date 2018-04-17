package viewModels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import entidades.Empresa;
import entidades.Metodologia;
import entidades.Resultado;

@Observable
public class ListaEmpresasEvaluadas {

	private Metodologia metodologia;
	private Integer anio;
	private List<Empresa> empresas = new ArrayList<Empresa>();
	private List<List<Resultado>> listaResultados = new ArrayList<List<Resultado>>();
	private List<Resultado> resultados = new ArrayList<Resultado>();
	EntityManager em = PerThreadEntityManagers.getEntityManager();
	
	
	public ListaEmpresasEvaluadas() {
			this.empresas = obtenerEmpresas();
	}

	//se obtienen las listas de listas de resultados y se aplanan a una sola lista de resultados
	public void crearListaDeResultados() {
		this.listaResultados = empresas.stream().
				map(empresa -> metodologia.evaluarEmpresa(anio, empresa)).collect(Collectors.toList());
		resultados = this.aplanarLosResultados(listaResultados);
	}

	
	private List<Resultado> aplanarLosResultados(List<List<Resultado>> listaDeLista) {
		List<Resultado> listaAplanada = new ArrayList<Resultado>();
		listaDeLista.stream().forEach(lista -> listaAplanada.addAll(lista));
		return listaAplanada;
	}

	
	//obtiene las empresas y trae luego los periodos y las cuentas
	private List<Empresa> obtenerEmpresas() {
		List<Empresa> empresasACompletar = new ArrayList<Empresa>();
		empresasACompletar = em.createQuery("from Empresa").getResultList();
		
		//hacer que hibernate se traiga todas las empresas con periodos y cuentas
		empresasACompletar.stream().forEach(empresa -> empresa.getPeriodos().forEach(periodo -> periodo.getCuentas().size()));
		
		return empresasACompletar;
	}
	
	
	
	
	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}
	
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public List<Resultado> getResultados() {
		return resultados;
	}
	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}
	
	
}
