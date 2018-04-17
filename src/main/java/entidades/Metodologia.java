package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import condicionesImpuestasPorElUser.MetodologiaBuffet;
import excepciones.CondicionException;

@Entity
@Table(name = "metodologias") 
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "metodologia")
@DiscriminatorValue(value = "metodologia")
public class Metodologia {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_metodologia")
	@JsonIgnore
	private Integer metodologiaId;
	
	
	// atributos
	@Column(name = "nombre", columnDefinition = "varchar(63)")
	protected String nombre;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
    @JoinColumn(name="id_metodologia")
	private List<Condicion> condiciones = new ArrayList<Condicion>();

	
	
	//constructor
	public Metodologia(String nombre2, List<Condicion> condicionesL) {
		this.nombre = nombre2;
		condiciones = condicionesL;
	}
	
	public Metodologia(String nombre2) {
		this.nombre = nombre2;
	}
	
	//constructor jackson (prueba)
	public Metodologia(){}
	
	//getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void agregarCondicion(Condicion condicion) {
		condiciones.add(condicion);
	}
	

	public List<Resultado> evaluarEmpresa(Integer anio, Empresa empresa){
		List<Resultado> resultados = new ArrayList<Resultado>(); 
		return condiciones.stream().map(condicion -> 
											 new Resultado(
												empresa.getNombre(),
												condicion.getNombre(),
												condicion.evaluarEmpresa(anio, empresa)))
						.collect(Collectors.toList());
	}
	
	public String toString()
	{
		return this.nombre;
	}
		

}
