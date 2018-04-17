package entidades;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.uqbar.commons.utils.Observable;


@Entity
@Table(name = "cuentas")
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_cuenta")
	@JsonIgnore
	private Integer cuentaId;
	
	//atributos
	@Column(name = "nombre" , columnDefinition = "varchar(63)")
	private String nombre;
	@Column(name = "valor")
	private Double valor;
	
	// Constructor (jackson)
	public Cuenta() {
	}
	
	//Constructor 
	public Cuenta(String nombre, Double valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}
	
	//metodos
	
	
	
		//getters y setters

	public Integer getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(Integer cuentaId) {
		this.cuentaId = cuentaId;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	//metodo para arena
	public String toString()
	{
		return this.nombre;
	}

}
