package viewModels;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import entidades.Empresa;
import entidades.Indicador;
import entidades.Periodo;
import repositorios.RepositorioEmpresas;
import repositorios.RepositorioIndicadores;

@Observable
public class ConsultarIndicador {

	//atributos
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private Periodo periodoSeleccionado;
	private List<Indicador> indicadores;
	private Indicador indicadorSeleccionado;
	 EntityManager em = PerThreadEntityManagers.getEntityManager();
	Double valor;
	

	
	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
			this.valor = valor;			
	}


	public ConsultarIndicador() {
		this.empresas = this.obtenerEmpresas();
		this.indicadores = RepositorioIndicadores.indicadores;
		this.valor=0.00;
	}
	
	
	


	private List<Empresa> obtenerEmpresas() {
		return em.createQuery("from Empresa").getResultList();
	}
	
	public void ConsultarCuenta() {
	    try {
			this.empresas = obtenerEmpresas();

	    } catch (Exception e) {
	    	e.printStackTrace();
	    } 
	}

	
	//getters y setters
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	
	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}
	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}
	public Periodo getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}
	public void setPeriodoSeleccionado(Periodo periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}
	public List<Indicador> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}
	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}
	

	
	
}
