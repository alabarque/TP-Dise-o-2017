package viewModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import condicionesImpuestasPorElUser.CondicionCompararIndicadorDeDosEmpresas;
import condicionesImpuestasPorElUser.CondicionCrecimientoIndicador;
import entidades.Indicador;
import entidades.Metodologia;
import excepciones.MetodologiaException;
import operacionDeCondiciones.Orden;
import repositorios.RepositorioIndicadores;

@Observable
public class CondicionDeCrecimientoIndicador {

	private Metodologia metodologia;
	private Integer intervalo;
	private List<Indicador> indicadores = new ArrayList<Indicador>();
	private List<Orden> ordenes =  new ArrayList<Orden>(Arrays.asList(Orden.CRECIENTE, Orden.DECRECIENTE));
	private Indicador indicadorSeleccionado;
	private Orden ordenSeleccionado;
	
	//constructor, carga las empresas e indicadores
	public CondicionDeCrecimientoIndicador() {
				this.indicadores = RepositorioIndicadores.indicadores;
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
	public List<Indicador> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	public List<Orden> getOrdenes() {
		return ordenes;
	}
	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}
	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}
	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}
	public Orden getOrdenSeleccionado() {
		return ordenSeleccionado;
	}
	public void setOrdenSeleccionado(Orden ordenSeleccionado) {
		this.ordenSeleccionado = ordenSeleccionado;
	}
	
	
	
	



	public void agregarCondicion() throws MetodologiaException {
		try{
			this.verificarNoNulos();
			metodologia.agregarCondicion(
					new CondicionCrecimientoIndicador(
							intervalo, indicadorSeleccionado.getNombre(), ordenSeleccionado));
		}catch (MetodologiaException me){
			me.printStackTrace();
			throw new MetodologiaException("complete los campos vacios");
		}
	}


	private void verificarNoNulos() throws MetodologiaException {
		if (indicadorSeleccionado == null || intervalo == null || ordenSeleccionado == null){
			MetodologiaException metodologiaException = new MetodologiaException("");
			throw metodologiaException;
		}
		
	}
	
	
	

}
