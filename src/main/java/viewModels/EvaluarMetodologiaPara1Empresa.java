package viewModels;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import entidades.Empresa;
import entidades.Metodologia;
import entidades.Resultado;
import excepciones.BDException;
import excepciones.MetodologiaException;

@Observable
public class EvaluarMetodologiaPara1Empresa {

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private List<Metodologia> metodologias;
	private Metodologia metodologiaSeleccionada;
	private String anio;
	private List<Resultado> resultadoMetodologia;
	EntityManager em = PerThreadEntityManagers.getEntityManager();
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	public List<Resultado> getResultadoMetodologia() {
		return resultadoMetodologia;
	}
	public void setResultadoMetodologia(List<Resultado> resultadoMetodologia) {
		this.resultadoMetodologia = resultadoMetodologia;
	}
	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}
	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getAnio(){
		return anio;
	}
	public List<Metodologia> getMetodologias() {
		return metodologias;
	}
	public void setMetodologias(List<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}
	public Metodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}
	public void setMetodologiaSeleccionada(Metodologia metodologia) {
		this.metodologiaSeleccionada = metodologia;
	}
	
	public EvaluarMetodologiaPara1Empresa() {
	    try {
			this.empresas = obtenerEmpresas();
			this.metodologias = obtenerMetodologias();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } 
	}

	private List<Metodologia> obtenerMetodologias() {
		return em.createQuery("from Metodologia").getResultList();
	}

	private List<Empresa> obtenerEmpresas() {
		return em.createQuery("from Empresa").getResultList();
	}
	

	public void consultarMetodologia() throws MetodologiaException{
		try{
			this.verificarNoNulos();
			
			//hacer que hibernate se traiga todos periodos y cuentas
			empresaSeleccionada.getPeriodos().forEach(periodo -> periodo.getCuentas().size());
			
			
			resultadoMetodologia = metodologiaSeleccionada.
					evaluarEmpresa(Integer.parseInt(anio), empresaSeleccionada);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new MetodologiaException("el numero es muy grande");
		} catch (MetodologiaException me){
			throw new MetodologiaException("complete los campos vacios");
		}
	}
	
	
	private void verificarNoNulos() throws MetodologiaException {
		if(metodologiaSeleccionada == null ||
				empresaSeleccionada == null ||
				anio == null){
			MetodologiaException metodologiaException = new MetodologiaException("");
			throw metodologiaException;
		}
	}
}
