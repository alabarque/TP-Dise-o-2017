package viewModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import condicionesImpuestasPorElUser.CondicionCompararIndicadorDeDosEmpresas;
import entidades.Empresa;
import entidades.Indicador;
import entidades.Metodologia;
import excepciones.MetodologiaException;
import repositorios.RepositorioIndicadores;

@Observable
public class CondicionDeCompararIndicador {

	private Metodologia metodologia;
	private List<String> comparadores =  new ArrayList<String>(Arrays.asList("mayor", "menor"));
	private List<Empresa> empresas = new ArrayList<Empresa>();
	private List<Indicador> indicadores = new ArrayList<Indicador>();
	private Indicador indicadorSeleccionado;
	private Empresa empresaSeleccionada;
	private String comparadorSeleccionado;
	EntityManager em = PerThreadEntityManagers.getEntityManager();
	
	//constructor, carga las empresas e indicadores
	public CondicionDeCompararIndicador() {
		try {
			this.empresas = obtenerEmpresas();
			this.indicadores = RepositorioIndicadores.indicadores;
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	
	
	
	public Metodologia getMetodologia() {
		return metodologia;
	}


	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}


	public String getComparadorSeleccionado() {
		return comparadorSeleccionado;
	}


	public void setComparadorSeleccionado(String comparadorSeleccionado) {
		this.comparadorSeleccionado = comparadorSeleccionado;
	}


	public List<String> getComparadores() {
		return comparadores;
	}


	public void setComparadores(List<String> comparadores) {
		this.comparadores = comparadores;
	}


	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}


	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}


	public List<Indicador> getIndicadores() {
		return indicadores;
	}


	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}


	public List<Empresa> getEmpresas() {
		return empresas;
	}


	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}


	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}


	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}
	

	private List<Empresa> obtenerEmpresas() {
		return em.createQuery("from Empresa").getResultList();
	}


	public void agregarCondicion() throws MetodologiaException {
		try{
			this.verificarNoNulos();
			metodologia.agregarCondicion(
					new CondicionCompararIndicadorDeDosEmpresas(
							indicadorSeleccionado.getNombre(), comparadorSeleccionado, empresaSeleccionada));
		}catch (MetodologiaException me){
			me.printStackTrace();
			throw new MetodologiaException("complete los campos vacios");
		}
	}


	private void verificarNoNulos() throws MetodologiaException {
		if(indicadorSeleccionado == null ||
				empresaSeleccionada == null ||
				comparadorSeleccionado == null){
			MetodologiaException metodologiaException = new MetodologiaException("");
			throw metodologiaException;
		}
	}
		
}
