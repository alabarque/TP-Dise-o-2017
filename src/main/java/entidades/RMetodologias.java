package entidades;

import java.util.ArrayList;
import java.util.List;

public class RMetodologias {

	Empresa empresa;
	List<Resultado> resultados = new ArrayList<Resultado>();
	Integer anio;
	Metodologia metodologia;
	
	public RMetodologias (Empresa empresa, Integer anio, Metodologia metodologia, List<Resultado> resultados){
		super();
		this.empresa = empresa;
		this.anio = anio;
		this.metodologia = metodologia;
		this.resultados = resultados;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public Integer getAnio() {
		return anio;
	}
	
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	public Metodologia getMetodologia() {
		return metodologia;
	}
	
	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}
}
