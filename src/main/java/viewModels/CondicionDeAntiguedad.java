package viewModels;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import condicionesImpuestasPorElUser.CondicionAntiguedadEntreEmpresas;
import entidades.Empresa;
import entidades.Metodologia;
import excepciones.MetodologiaException;

@Observable
public class CondicionDeAntiguedad {
	
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private Metodologia metodologia;
	EntityManager em = PerThreadEntityManagers.getEntityManager();

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
		
	}

	public Metodologia getMetodologia() {
		return metodologia;
	}
	
	public void agregarCondicion() throws MetodologiaException {
		if (empresaSeleccionada == null){
			throw new MetodologiaException("elija una empresa");
		}
		metodologia.agregarCondicion(new CondicionAntiguedadEntreEmpresas(empresaSeleccionada));
		
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
	
	public CondicionDeAntiguedad() {
	    try {
			this.empresas = obtenerEmpresas();

	    } catch (Exception e) {
	    	e.printStackTrace();
	    } 
	}

	private List<Empresa> obtenerEmpresas() {
		return em.createQuery("from Empresa").getResultList();
	}


}
