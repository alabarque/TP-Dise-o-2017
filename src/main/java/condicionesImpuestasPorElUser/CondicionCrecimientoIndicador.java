package condicionesImpuestasPorElUser;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import entidades.Condicion;
import entidades.Empresa;
import excepciones.CondicionException;
import operacionDeCondiciones.ObtenerValorIndicador;
import operacionDeCondiciones.Operacion;
import operacionDeCondiciones.Orden;


@Entity
@Table(name = "condicioncrecimientoindicador")
public class CondicionCrecimientoIndicador extends Condicion{

	/*
	 * Esta condicion evalua el valor
	 * del indicador en distintos periodos
	 * por un intervalo de tiempo y
	 * chequea que sea consistentemente
	 * creciente o decreciente, dependiendo del
	 * orden dado por el usuario
	 */
	
	@Column(name = "intervalo")
	Integer intervaloAnios;
	
	@Column(name = "indicador")
	String indicador;
	
	@Column(name = "orden")
	Orden orden;
	
	
	//constructor
	//faltaria abstraer el intervalo para poder chequear que no sea cero
	//ya que se busca que se evalue mas de un indicador
	public CondicionCrecimientoIndicador(Integer intervalo, String indicadorAComparar, Orden crecienteODecreciente){
		intervaloAnios = intervalo;	
		indicador = indicadorAComparar;
		orden = crecienteODecreciente;
		this.nombre = "Crecimiento Indicador";
	}
	
	public CondicionCrecimientoIndicador (){}
	

	//evalua el indicador para la empresa por cierto intervalo de tiempo
	@Override
	public Boolean evaluarEmpresa(Integer anioLimite, Empresa empresa){
		ObtenerValorIndicador auxiliar = new ObtenerValorIndicador();
		List<Double> valoresIndicador;
		valoresIndicador = auxiliar.ejecutar(empresa, anioLimite, indicador, intervaloAnios);
		if(valoresIndicador.size() < intervaloAnios + 1){
			return false;
		}else{
			return this.cumpleCondicionCrecimiento(valoresIndicador);
		}
	}

	
	//chequea que los siguientes valores de la lista sean mayores o menores segun el
	//criterio de ordenamiento dado
	private Boolean cumpleCondicionCrecimiento(List<Double> valoresIndicador) {
		
		if(orden.equals("creciente")){
			return this.crecimientoConsistente(valoresIndicador);
		}
		
		else{
			
			//invierte el orden los valores de la lista y los evalua de forma creciente
			Collections.reverse(valoresIndicador);
			
			return this.crecimientoConsistente(valoresIndicador);
		}
		
	}
	
	
	//encontrar como hacer esto sin un for y un if
	//chequea que cada valor sea menor al siguiente de la lista
	//si no lo es, sale del ciclo y devuelve true.
	private boolean crecimientoConsistente(List<Double> valores){
		Boolean esCreciente = false;
		for (int j = 0; j < valores.size(); j++) {
			if (esCreciente){
				break;
			}
			else{
				esCreciente = valores.get(j) > valores.get(j+1);
			}
		}
		return esCreciente;
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


	public Orden getOrden() {
		return orden;
	}


	public void setOrden(Orden orden) {
		this.orden = orden;
	}

}
