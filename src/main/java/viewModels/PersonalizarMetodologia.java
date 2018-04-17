package viewModels;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import entidades.Condicion;
import entidades.Metodologia;

@Observable
public class PersonalizarMetodologia {
	private List<Condicion> condiciones;
	private Metodologia metodologia;
	private String condicionSeleccionada;
	
	public Metodologia getMetodologia() {
		return metodologia;
	}
	
	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}
	
	public void agregarCondicion(Condicion condicion){
		condiciones.add(condicion);
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

	public String getCondicionSeleccionada() {
		return condicionSeleccionada;
	}

	public void setCondicionSeleccionada(String condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
	}

	public void crearMetodologia(String nombre) {
		metodologia = new Metodologia(nombre);
	}

	public boolean noTieneCondiciones() {
		return metodologia.getCondiciones().size() == 0;
	}
	

	
	
}
