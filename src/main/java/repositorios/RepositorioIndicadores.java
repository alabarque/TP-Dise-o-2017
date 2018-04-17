package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import entidades.Empresa;
import entidades.Indicador;
import entidades.Periodo;
import excepciones.IndicadorException;

public class RepositorioIndicadores implements WithGlobalEntityManager {

	public static List<Indicador> indicadores = new ArrayList<Indicador>();
	public static RepositorioIndicadores instancia = new RepositorioIndicadores();
	
	public void filtrarPorUsuario(int usuario) {
		Query query = entityManager().createQuery("from Indicador where (id_usuario = :id) or (id_usuario = 0)",Indicador.class);
		query.setParameter("id", usuario);
		indicadores = query.getResultList();
	}
	
	public static List<Indicador> getIndicadores() {
		return indicadores;
	}

	public RepositorioIndicadores(List<Indicador> indicadores) {
		super();
		RepositorioIndicadores.indicadores = indicadores;
	}
	
	public RepositorioIndicadores(){}

	public static void agregarIndicador(Indicador indicador) {
		indicadores.add(indicador);
	}

	public static boolean tieneElementos() {
		return (indicadores.size() > 0);
	}

	public static boolean existeIndicador(String nombre) {
		return (indicadores.stream().anyMatch(indicador -> indicador.getNombre().equals(nombre)));
	}

	public static double evaluarIndicador(String nombre, Periodo periodo) throws IndicadorException {

		double resultado = 0;
			
		if (RepositorioIndicadores.existeIndicador(nombre)) {
				resultado = (indicadores.stream().filter(indicador -> 
									indicador.getNombre().equals(nombre))
								.findFirst().get())
							.evaluar(periodo);
				return resultado;
			}
		else
		{
		throw new IndicadorException("No Existe el Indicador");
		}
	}

	public static double evaluarIndicador(Indicador indicador, Periodo periodo) throws IndicadorException {

		double resultado = 0;
			
		try
		{
				resultado = indicador.evaluar(periodo);
				return resultado;
			}
		catch(Exception e)
		{
		throw new IndicadorException("No es Valido el Jndicador");
		}
	}
	
	public static void agregarIndicadores(List<Indicador> listaIndicadores) {
		indicadores.addAll(listaIndicadores);
		
	}
}
