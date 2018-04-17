package operacionDeCondiciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entidades.Empresa;
import entidades.Periodo;
import excepciones.IndicadorException;
import excepciones.TipoDeOrdenIncorrectoException;
import repositorios.RepositorioIndicadores;

public class ObtenerValorIndicador {
	
	//retorna una lista de valores de indicadores
	public List<Double> ejecutar(Empresa UnaEmpresa, Integer anio, Integer cantAnios, Orden orden, String indicador){
		List<Double> indicadores;
		FiltrarYOrdernarLista filtrador = new FiltrarYOrdernarLista();
		List<Periodo> listaOrdenada = new ArrayList<Periodo>();
		try {
			listaOrdenada = filtrador.ejecutar(UnaEmpresa, anio,cantAnios, orden);
		} catch (TipoDeOrdenIncorrectoException e) {
			e.printStackTrace();
		}

		indicadores = listaOrdenada.stream()
				.mapToDouble(periodo -> obtenerValor(periodo,indicador))
				.boxed().collect(Collectors.toList());

		return indicadores;
	}
	

	//retorna una lista de valores de indicadores
	public List<Double> ejecutar(Empresa UnaEmpresa, Integer anio, String indicador, Integer cantAnios){
		List<Double> indicadores = new ArrayList<Double>();
		FiltrarYOrdernarLista filtrador = new FiltrarYOrdernarLista();
		List<Periodo> listaOrdenada = filtrador.ejecutar(UnaEmpresa, anio,cantAnios);

		indicadores = listaOrdenada.stream()
									.mapToDouble(periodo -> obtenerValor(periodo,indicador))
									.boxed().collect(Collectors.toList());

		return indicadores;
	}

	public double obtenerValor(Periodo periodo, String indicador){
		double indicadorPeriodo = 0;
		try {
			return RepositorioIndicadores.evaluarIndicador(indicador, periodo);
		} catch (IndicadorException e) {
			e.printStackTrace();
		}
		return indicadorPeriodo;
	}

}
