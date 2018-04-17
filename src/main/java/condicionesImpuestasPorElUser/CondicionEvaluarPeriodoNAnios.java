package condicionesImpuestasPorElUser;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import entidades.Condicion;
import entidades.Empresa;
import operacionDeCondiciones.ObtenerValorIndicador;
import operacionDeCondiciones.Operacion;

@Entity
@Table (name = "condicion_indicador_en_periodo_n_anios")
public class CondicionEvaluarPeriodoNAnios extends Condicion{

	
	/*
	 * esta condicion puede evaluar
	 * a) que los mismos tipos de indicadores sean mayores/menores a un valor en los ultimos N años.
	 * b) que un promedio, sumatoria o mediana de indicadores sea mayor/menor a cierto valor.
	 * Tambien puede hacer a) y b) a la vez.
	 * 
	 * Nota: si se da el caso de a) solamente se debe elegir como Operacion: ningunaOperacion.
	 */
	
	@Column(name = "intervaloAnios")
	Integer intervaloAnios;
	
	@Column(name = "indicador")
	String indicador;
	
	@Column(name = "valor")
	Integer valor;
	
	@Column(name = "comparador", columnDefinition = "varchar(8)")
	String comparador;
	
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	@JoinColumn(name = "id_condicion")
	Operacion operacion;
	
	//constructor
	public CondicionEvaluarPeriodoNAnios(Integer intervalo, String indicadorAComparar, Integer valorAComparar, 
			String compararMayorOMenor, Operacion operatoria){
		intervaloAnios = intervalo;
		indicador = indicadorAComparar;
		valor = valorAComparar;
		comparador = compararMayorOMenor;
		operacion = operatoria;
		this.nombre = "Condicion evaluar periodo por N anios";
	}
	
	//para hibernate
	public CondicionEvaluarPeriodoNAnios(){}
	

	//Aca se evalua el indicador en la empresa
	//Se delega la operacion a alguna subclase de Operacion
	//  y se llama a cumpleCondicionElConjuntoDeValores o a cumpleCondicion
	public Boolean evaluarEmpresa(Integer anioLimite, Empresa empresa) {
		ObtenerValorIndicador auxiliar = new ObtenerValorIndicador();
		List<Double> valoresIndicador;
		valoresIndicador = auxiliar.ejecutar(empresa, anioLimite, indicador, intervaloAnios);
		if (valoresIndicador.size() < intervaloAnios + 1){
			return false;
		}else{	
			return operacion.operar(valoresIndicador, this);
		}
	}
	
	
	
	//poco performante, arreglar despues
	public Boolean cumpleCondicionElConjuntoDeValores(List<Double> valoresIndicador) {
		return valoresIndicador.stream().allMatch(valor -> this.cumpleCondicion(valor));
	}
	
	
	public Boolean cumpleCondicion(Double valorIndicador){
		if (comparador == "mayor"){
			return valorIndicador > valor;
		}
		else{
			return valorIndicador < valor;
		}
	}


	public Integer getIntervaloAnios() {
		return intervaloAnios;
	}


	public void setIntervaloAnios(Integer intervaloAnios) {
		this.intervaloAnios = intervaloAnios;
	}


	public String getIndicador() {
		return indicador;
	}


	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}


	public Integer getValor() {
		return valor;
	}


	public void setValor(Integer valor) {
		this.valor = valor;
	}


	public String getComparador() {
		return comparador;
	}


	public void setComparador(String comparador) {
		this.comparador = comparador;
	}


	public Operacion getOperacion() {
		return operacion;
	}


	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}
}
