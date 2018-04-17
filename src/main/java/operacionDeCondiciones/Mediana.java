package operacionDeCondiciones;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import condicionesImpuestasPorElUser.CondicionEvaluarPeriodoNAnios;
import entidades.Periodo;


@Entity
@DiscriminatorValue(value="mediana") 
public class Mediana extends Operacion {

	public Mediana() {
		this.nombre = "mediana";
	}
	
	@Override
	public Boolean operar(List<Double> list, CondicionEvaluarPeriodoNAnios condicion) {
	    int mediana = list.size()/2;
	    Double valorMediana;
	    if (list.size()%2 == 1) {
	        valorMediana = list.get(mediana);
	        return condicion.cumpleCondicion(valorMediana);
	    } else {
	        valorMediana = list.get(mediana-1) + list.get(mediana) / 2.0;
	        return condicion.cumpleCondicion(valorMediana);
	    }
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
