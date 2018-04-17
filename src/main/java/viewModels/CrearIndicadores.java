package viewModels;

import org.uqbar.commons.utils.Observable;

import entidades.Indicador;
import repositorios.RepositorioIndicadores;

@Observable
public class CrearIndicadores {
	
	private String nombre ;
	private String formula ;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Indicador crearIndicador()
	{
		Indicador indicador = new Indicador(this.nombre,this.formula,0);
		RepositorioIndicadores.agregarIndicador(indicador);
		return indicador;
	}

}
