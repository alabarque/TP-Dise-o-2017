package condicionesImpuestasPorElUser;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import entidades.Condicion;
import entidades.Empresa;
import entidades.Metodologia;
import operacionDeCondiciones.Orden;

@Entity
@DiscriminatorValue(value = "buffet")
public class MetodologiaBuffet extends Metodologia{
	
	/*
	 * Dado un anio y una empresa como referencia
	 * se evalua para otras empresas si...
	 * ... la ROE de ese anio es mejor que la de referencia
	 * ... tiene menor deuda que la de referencia
	 * ... tiene margenes crecientes
	 * ... mas de diez anios
	 * ... es mas antiguedad que la de referencia
	 */
	
	public MetodologiaBuffet(String nombre2) {
		super("buffet " + nombre2);
	}
	
	public MetodologiaBuffet(){}

	public void crearMetodologiaBuffet(Empresa otraEmpresa){
		CondicionCompararIndicadorDeDosEmpresas maxROE = new CondicionCompararIndicadorDeDosEmpresas("ROE", "mayor", otraEmpresa);
		CondicionCompararIndicadorDeDosEmpresas deudaMinima = new CondicionCompararIndicadorDeDosEmpresas("deuda", "menor", otraEmpresa);
		CondicionCrecimientoIndicador margenesCrecientes = new CondicionCrecimientoIndicador(10, "margen", Orden.CRECIENTE);
		CondicionLongevidad longevidad = new CondicionLongevidad();
		CondicionAntiguedadEntreEmpresas antiguedad = new CondicionAntiguedadEntreEmpresas(otraEmpresa);

		this.agregarCondicion(maxROE);
		this.agregarCondicion(deudaMinima);
		this.agregarCondicion(margenesCrecientes);
		this.agregarCondicion(longevidad);
		this.agregarCondicion(antiguedad);
	}	
}
