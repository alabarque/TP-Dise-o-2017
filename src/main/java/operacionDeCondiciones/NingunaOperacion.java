package operacionDeCondiciones;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import condicionesImpuestasPorElUser.CondicionEvaluarPeriodoNAnios;

@Entity
@DiscriminatorValue(value="nada") 
public class NingunaOperacion extends Operacion{

	
	public NingunaOperacion(){
		this.nombre = "no realiza operacion";
	}
	
	@Override
	public Boolean operar(List<Double> listaValores, CondicionEvaluarPeriodoNAnios condicion) {
		return condicion.cumpleCondicionElConjuntoDeValores(listaValores);
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
