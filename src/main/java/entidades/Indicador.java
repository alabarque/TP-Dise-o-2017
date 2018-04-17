package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

import excepciones.IndicadorException;
import parser.ParserIndicadores;

 
@Entity
@Table(name = "indicadores")
public class Indicador {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_indicador")
	@JsonIgnore
	private Integer indicadorId;
	
	//atributos
	@Column(name = "nombre", columnDefinition = "varchar(63)")
	private String nombre ;
	
	@Column(name = "formula")
	private String formula ;
	
	@Column(name = "id_usuario") 
	private int id_usuario ;
	
	
	public Indicador(String nombre, String formula , int id_usuario ) {
		super();
		this.nombre = nombre;
		this.formula = formula;
		this.id_usuario = id_usuario;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre.replace(" ","");
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	
	// Constructor (jackson)
		public Indicador() {
			
		}
		
	//metodo para arena
		public String toString()
		{
			return this.nombre;
		}
		
		//metodo para arena
		public double evaluar(Periodo periodo) throws IndicadorException
		{
			return ParserIndicadores.evaluar(this.getFormula(), periodo);
		}
		
}
