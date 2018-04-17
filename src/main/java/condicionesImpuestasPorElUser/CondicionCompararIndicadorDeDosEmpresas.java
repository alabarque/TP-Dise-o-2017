package condicionesImpuestasPorElUser;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import entidades.Condicion;
import entidades.Empresa;
import entidades.Indicador;
import operacionDeCondiciones.ObtenerValorIndicador;


@Entity
@Table(name = "condicion_comparar_indicador_empresas")
public class CondicionCompararIndicadorDeDosEmpresas extends Condicion{
	
	/*
	 * Esta condicion evalua dos empresas
	 * 	para un mismo periodo
	 * 	en un intervalo de un año
	 * 	y devuelve la empresa que tenga el
	 * 	mejor indicador segun el comparador
	 */
	
	@Column(name = "intervalo")
	Integer intervaloAnios = 1;
	
	@Column(name = "comparador", columnDefinition = "varchar(12)")
	String comparador;
	
	@Column(name = "indicador")
	String indicador;
	
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	Empresa empresaAComparar;

	
	public CondicionCompararIndicadorDeDosEmpresas(String indicadorComparado, String compararMayorOMenor, 
		Empresa empresaB){
		indicador = indicadorComparado;
		comparador = compararMayorOMenor;
		this.empresaAComparar = empresaB;
		this.nombre = "Comparar Indicador de la Empresa vs Otra";
	}
	
	
	//para hibernate
	public CondicionCompararIndicadorDeDosEmpresas(){}
	
	

	public Boolean evaluarEmpresa(Integer anioLimite, Empresa empresaEvaluada) {
		if (tieneElPeriodo(anioLimite, empresaEvaluada)){
			return false;
		}else 
		if(tieneElPeriodo(anioLimite, empresaAComparar)){
			return false;
		}else{
			Double valorIndicadorEmpresaEvaluada = this.valoresIndicadorPorPeriodo(anioLimite, empresaEvaluada);
			Double valorIndicadorB = this.valoresIndicadorPorPeriodo(anioLimite, empresaAComparar);
			return this.esMejorElIndicadorDeLaEmpresaEvaluada(valorIndicadorEmpresaEvaluada, valorIndicadorB);
		}
	}


	private boolean tieneElPeriodo(Integer anioLimite, Empresa empresaEvaluada) {
		return empresaEvaluada.getPeriodos().stream().anyMatch(periodo -> periodo.getPeriodo() == anioLimite);
	}

	public Double valoresIndicadorPorPeriodo(Integer anioLimite, Empresa empresa){
		ObtenerValorIndicador auxiliar = new ObtenerValorIndicador();
		Double valorIndicador;
		valorIndicador = auxiliar.ejecutar(empresa, anioLimite, indicador, intervaloAnios).get(0);
		return valorIndicador;
	}
	
	
	private Boolean esMejorElIndicadorDeLaEmpresaEvaluada(Double valorIndicadorA, Double valorIndicadorB) {
		if (comparador == "mayor"){
			return valorIndicadorA > valorIndicadorB;
		}
		else{
			return valorIndicadorA < valorIndicadorB;
		}
	}



	public Integer getIntervaloAnios() {
		return intervaloAnios;
	}



	public void setIntervaloAnios(Integer intervaloAnios) {
		this.intervaloAnios = intervaloAnios;
	}



	public String getComparador() {
		return comparador;
	}



	public void setComparador(String comparador) {
		this.comparador = comparador;
	}



	public String getIndicador() {
		return indicador;
	}



	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	
}
