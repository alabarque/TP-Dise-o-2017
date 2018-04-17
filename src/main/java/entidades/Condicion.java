package entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.uqbar.commons.utils.Observable;

import excepciones.CondicionException;

@Entity
@Table(name = "condiciones")
@Inheritance(strategy=InheritanceType.JOINED)
public class Condicion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_condicion")
	@JsonIgnore
	private Integer condicionId;
	
	
	// atributos
	@Column(name = "nombre", columnDefinition = "varchar(63)")
	protected String nombre = "nombre";
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombreCondicion) {
		this.nombre = nombreCondicion;
	}
	
	public Boolean evaluarEmpresa(Integer anio, Empresa empresa){
		return false;
	}
	
	public String toString()
	{
		return this.nombre;
	}

}
