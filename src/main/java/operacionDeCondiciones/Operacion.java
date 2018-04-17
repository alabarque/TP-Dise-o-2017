package operacionDeCondiciones;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import condicionesImpuestasPorElUser.CondicionEvaluarPeriodoNAnios;
import entidades.Periodo;


@Entity
@Table(name = "operaciones")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name="tipo_operacion") 
public class Operacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_condicion")
	@JsonIgnore
	private Integer condicionId;
	
	
	// atributos
	@Column(name = "nombre")
	protected String nombre = "nombre";
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Boolean operar(List<Double> listaValores, CondicionEvaluarPeriodoNAnios condicion){
		return false;
	}

}
