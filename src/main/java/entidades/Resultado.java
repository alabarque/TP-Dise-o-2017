package entidades;

import org.uqbar.commons.utils.Observable;

@Observable
public class Resultado {

	
	String nombreEmpresa;
	String nombreCondicion;
	Boolean resultado;
	
	
	public Resultado(String nombreE, String nombreC, Boolean resultado){
		this.nombreEmpresa = nombreE;
		this.nombreCondicion = nombreC;
		this.resultado = resultado;
	}
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getNombreCondicion() {
		return nombreCondicion;
	}
	public void setNombreCondicion(String nombreMetodologia) {
		this.nombreCondicion = nombreMetodologia;
	}
	public Boolean getResultado() {
		return resultado;
	}
	public void setResultado(Boolean resultado) {
		this.resultado = resultado;
	}
}
