package operacionDeCondiciones;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import condicionesImpuestasPorElUser.CondicionEvaluarPeriodoNAnios;
import entidades.Periodo;

@Entity
@DiscriminatorValue(value="sumatoria") 
public class Sumatoria extends Operacion {

	public Sumatoria(){
	this.nombre = "sumatoria";
	}
	
	@Override
	public Boolean operar(List<Double> lista, CondicionEvaluarPeriodoNAnios condicion) {
		Double sumatoria = lista.stream().reduce(0.0, (a,b) -> (a+b));
		return condicion.cumpleCondicion(sumatoria);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String toString()
	{
		return this.nombre;
	}
		
}
