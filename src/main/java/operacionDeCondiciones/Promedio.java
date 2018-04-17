package operacionDeCondiciones;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import condicionesImpuestasPorElUser.CondicionEvaluarPeriodoNAnios;
import entidades.Periodo;


@Entity
@DiscriminatorValue(value="promedio") 
public class Promedio extends Operacion {

	
	public Promedio(){
	this.nombre = "promedio";
	}
	
	@Override
	public Boolean operar(List<Double> listaValores, CondicionEvaluarPeriodoNAnios condicion) {
		Double promedio;
		promedio = listaValores.stream().reduce(0.0, (a,b) -> (a+b)) / listaValores.size();
		return condicion.cumpleCondicion(promedio);
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
