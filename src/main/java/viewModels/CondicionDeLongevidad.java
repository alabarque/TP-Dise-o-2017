package viewModels;

import org.uqbar.commons.utils.Observable;

import condicionesImpuestasPorElUser.CondicionLongevidad;
import entidades.Metodologia;

@Observable
public class CondicionDeLongevidad {
	
	private Metodologia metodologia;
	private String anio;
	
	
	public void agregarCondicion(){
		metodologia.agregarCondicion(new CondicionLongevidad(Integer.parseInt(anio)));
	}

	public Metodologia getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(Metodologia metodologiaACrear) {
		metodologia = metodologiaACrear;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	

}
