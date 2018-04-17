package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.uqbar.commons.utils.Observable;

import excepciones.IndicadorException;

@Observable 
@Entity
@Table(name = "periodos")
public class Periodo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_periodo")
	@JsonIgnore
	private Integer periodoId;
	
	//atributos
	@Column(name = "periodo")
	private Integer periodo;
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="id_periodo")
	private List<Cuenta> cuentas;
	
	public Integer getPeriodoId() {
		return periodoId;
	}

	public void setPeriodoId(Integer periodoId) {
		this.periodoId = periodoId;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas_periodo) {
		this.cuentas = cuentas_periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	
	public void agregarCuentas(Cuenta cuenta) {
		this.cuentas.add(cuenta);
	}
	
	public int cantCuentas() {
		return this.cuentas.size();
	}


	//Constructor
	public Periodo(Integer periodo){
		super();
		this.periodo = periodo;
		this.cuentas = new ArrayList<Cuenta>();
	}
	
	//metodos
	
	// Constructor (jackson)
	public Periodo() {

	}
	
	//Metodo del Selector de Arena
	public String toString()
	{
		return Integer.toString(this.periodo);
	}

	
	public boolean existeCuenta(String nombre)
	{
		return (this.getCuentas().stream().anyMatch(cuenta -> cuenta.getNombre().equals(nombre)));
	}
	
	public double obtenerValorCuenta(String nombre) throws IndicadorException
	{
		double resultado = 0;
		
			if(this.existeCuenta(nombre))
			{
				resultado=(this.getCuentas().stream().filter(cuenta -> cuenta.getNombre().equals(nombre)).findFirst().get()).getValor();
			}
			else
			{
				throw new IndicadorException("No Existe la Cuenta");
			}
			
		
		return resultado;
	}
	
	

}
