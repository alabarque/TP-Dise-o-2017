package operacionDeCondiciones;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import entidades.Empresa;
import entidades.Periodo;
import excepciones.TipoDeOrdenIncorrectoException;

public class FiltrarYOrdernarLista {

	Comparator<Periodo> creciente = (periodo1, periodo2) -> Integer.compare(periodo1.getPeriodo(),
			periodo2.getPeriodo());
	
	Comparator<Periodo> decreciente = (periodo1, periodo2) -> Integer.compare(periodo2.getPeriodo(),periodo1.getPeriodo()
			);
	
	public List<Periodo> ejecutar(Empresa unaEmpresa,Integer anio,Integer cantidadAnios,Orden orden) throws TipoDeOrdenIncorrectoException
	{
	List<Periodo> listaOrdenada = unaEmpresa.getPeriodos().stream()
			.filter(periodo -> periodosUltimosAnios(periodo,cantidadAnios, anio)).sorted(this.orden(orden)).collect(Collectors.toList());
	return listaOrdenada;
	
	
	}
	
	private Comparator<Periodo> orden(Orden orden){
		Comparator<Periodo> comparador = null;
		switch(orden){
		case CRECIENTE:
			comparador = this.creciente;
			break;
		case DECRECIENTE:
			comparador = this.decreciente;
			break;
		default:
			try {
				throw new TipoDeOrdenIncorrectoException("No es un orden creciente ni decreciente");
			} catch (TipoDeOrdenIncorrectoException e) {
				e.printStackTrace();
			}
		}
			return comparador;
	}

	private boolean periodosUltimosAnios(Periodo periodo, Integer cantidadAnios, Integer anio) {
		return periodo.getPeriodo() >= anio - cantidadAnios && periodo.getPeriodo() <= anio;
	}

	public List<Periodo> ejecutar(Empresa unaEmpresa, Integer anio,Integer cantAnios) {
		List<Periodo> listaOrdenada = unaEmpresa.getPeriodos().stream()
				.filter(periodo -> periodosUltimosAnios(periodo,cantAnios, anio)).collect(Collectors.toList());
		return listaOrdenada;
	}
}