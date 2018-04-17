package viewModels;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import entidades.Metodologia;
import excepciones.MetodologiaException;

@Observable
public class EvaluarMetodologiaParaNEmpresas {

	private List<Metodologia> metodologias;
	private Metodologia metodologiaSeleccionada;
	private String anio;
	EntityManager em = PerThreadEntityManagers.getEntityManager();
	
	
	
	public EvaluarMetodologiaParaNEmpresas(){
		metodologias = em.createQuery("from Metodologia").getResultList();
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
	public void setMetodologiaSeleccionada(Metodologia metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}




	public void verificarNoNulos() throws MetodologiaException {
		if(metodologiaSeleccionada == null ||
				anio == null){
			MetodologiaException metodologiaException = new MetodologiaException("complete los campos vacios");
			throw metodologiaException;
		}
	}

}
