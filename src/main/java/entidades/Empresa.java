package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.uqbar.commons.utils.Observable;

@Observable 
@Entity
@Table(name = "empresas")
public class Empresa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_empresa")
	@JsonIgnore
	private Integer empresaId;
	// atributos
	@Column(name = "nombre", columnDefinition = "varchar(63)")
	private String nombre;
	
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
    @JoinColumn(name="id_empresa")
	private List<Periodo> periodos = new ArrayList<Periodo>();

	
	// Constructor
	public Empresa(String nombre) {
		super();
		this.nombre = nombre;
		this.periodos = new ArrayList<Periodo>();
	}

	
	// metodos


	public void agregarPeriodos(Periodo periodos) {
		this.periodos.add(periodos);
	}

	public Integer getEmpresaId() {
		return empresaId;
	}


	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}


	public int cantPeriodos() {
		return this.periodos.size();
	}
	
	
		// getters y setters
	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	
	// Constructor (jackson)
	public Empresa() {
	}
	
	//metodo para arena
	public String toString()
	{
		return this.nombre;
	}
	
	
}
