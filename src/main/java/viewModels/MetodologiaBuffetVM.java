package viewModels;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import condicionesImpuestasPorElUser.MetodologiaBuffet;
import entidades.Empresa;
import entidades.Metodologia;
import excepciones.MetodologiaException;

@Observable
public class MetodologiaBuffetVM {

	private MetodologiaBuffet metodologia;
	private List<Empresa> empresas = new ArrayList<Empresa>();
	private Empresa empresaSeleccionada;
	EntityManager em = PerThreadEntityManagers.getEntityManager();
	
	
	public MetodologiaBuffet getMetodologiaBuffet() {
		return metodologia;
	}
	public void setMetodologiaBuffet(MetodologiaBuffet metodologia) {
		this.metodologia = metodologia;
	}
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<Empresa> empresa) {
		this.empresas = empresa;
	}
	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}
	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}
	

	

	public MetodologiaBuffetVM() {
	    try {
			this.empresas = obtenerEmpresas();

	    } catch (Exception e) {
	    	e.printStackTrace();
	    } 
	}

	private List<Empresa> obtenerEmpresas() {
		return em.createQuery("from Empresa").getResultList();
	}
	
	public Boolean noTieneEmpresa() {
		return empresaSeleccionada == null;
		
	}
	public void crearMetodologiaBuffet() {
		metodologia.crearMetodologiaBuffet(empresaSeleccionada);
		
	}
}
