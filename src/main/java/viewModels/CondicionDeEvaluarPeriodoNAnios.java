package viewModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import condicionesImpuestasPorElUser.CondicionCrecimientoIndicador;
import condicionesImpuestasPorElUser.CondicionEvaluarPeriodoNAnios;
import entidades.Indicador;
import entidades.Metodologia;
import excepciones.MetodologiaException;
import operacionDeCondiciones.Mediana;
import operacionDeCondiciones.NingunaOperacion;
import operacionDeCondiciones.Operacion;
import operacionDeCondiciones.Promedio;
import operacionDeCondiciones.Sumatoria;
import repositorios.RepositorioIndicadores;

@Observable
public class CondicionDeEvaluarPeriodoNAnios {

	private Metodologia metodologia;
	private Integer intervalo;
	private Integer valor;
	private List<Indicador> indicadores = new ArrayList<Indicador>();
	private List<String> comparadores = new ArrayList<String>(Arrays.asList("mayor", "menor"));
	private List<Operacion> operaciones = 
			new ArrayList<Operacion>(Arrays.asList(
					new Mediana(), new NingunaOperacion(), new Promedio(), new Sumatoria()));
	private Indicador indicadorSeleccionado;
	private String comparadorSeleccionado;
	private Operacion operacionSeleccionada;
	
	//constructor, obtiene los indicadores
	public CondicionDeEvaluarPeriodoNAnios(){
		indicadores = RepositorioIndicadores.indicadores;
	}
	
	
	public Metodologia getMetodologia() {
		return metodologia;
	}
	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}
	public Integer getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public List<Indicador> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	public List<String> getComparadores() {
		return comparadores;
	}
	public void setComparadores(List<String> comparadores) {
		this.comparadores = comparadores;
	}
	public List<Operacion> getOperaciones() {
		return operaciones;
	}
	public void setOperaciones(List<Operacion> operaciones) {
		this.operaciones = operaciones;
	}
	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}
	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}
	public String getComparadorSeleccionado() {
		return comparadorSeleccionado;
	}
	public void setComparadorSeleccionado(String comparadorSeleccionado) {
		this.comparadorSeleccionado = comparadorSeleccionado;
	}
	public Operacion getOperacionSeleccionada() {
		return operacionSeleccionada;
	}
	public void setOperacionSeleccionada(Operacion operacionSeleccionada) {
		this.operacionSeleccionada = operacionSeleccionada;
	}


	public void agregarCondicion() throws MetodologiaException {
		try{
			this.verificarNoNulos();
			metodologia.agregarCondicion(
					new CondicionEvaluarPeriodoNAnios(
							intervalo, indicadorSeleccionado.getNombre(), valor, comparadorSeleccionado, operacionSeleccionada));
		}catch (MetodologiaException me){
			me.printStackTrace();
			throw new MetodologiaException("complete los campos vacios");
		}
	}


	private void verificarNoNulos() throws MetodologiaException {
		if (indicadorSeleccionado == null || 
				intervalo == null || 
				comparadorSeleccionado == null ||
				operacionSeleccionada == null || 
				valor == null){
			MetodologiaException metodologiaException = new MetodologiaException("");
			throw metodologiaException;
		}
		
	}
	
	
	
}
