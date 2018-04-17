package entidades;

public class RIndicadores {
	
	String empresa;
	String anio; 
	String indicador; 
	Double valor;
	
	

	public RIndicadores(String empresa, String anio, String indicador, Double valor) {
		super();
		this.empresa = empresa;
		this.anio = anio;
		this.indicador = indicador;
		this.valor = valor;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

	

}
