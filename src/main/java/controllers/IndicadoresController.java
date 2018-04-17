package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import basedatos.GrabarIndicador;
import entidades.Empresa;
import entidades.Indicador;
import entidades.Periodo;
import excepciones.IndicadorException;
import parser.ParserIndicadores;
import repositorios.RepositorioEmpresas;
import repositorios.RepositorioIndicadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController {
	

	public ModelAndView indicadores(Request req, Response res) {
		
		Map<String, List<Empresa>> model = new HashMap<>();

		model.put("empresas", RepositorioEmpresas.getEmpresas());

		return new ModelAndView(model, "indicadores/indicadores.hbs");

	
	}
	
	
	
	
	//si viene con parametros vacios muestra pantalla de evaluar sino muestro evaluar+los resultados
	public ModelAndView consultar(Request req, Response res){
		String empresa = req.params("empresa");
		
		// crep hashmap para vista.		
		Map<String, Object> model = new HashMap<>();
		model.put("empresa", empresa);
		List<Periodo> periodos = RepositorioEmpresas.obtenerEmpresa(empresa).getPeriodos();
		model.put("periodos", periodos);
		return new ModelAndView(model, "/indicadores/evaluar.hbs");

	}
	
	public ModelAndView listarIndicadores(Request req,Response res)
	{
		
		//obtengo usuario
		int usuario = req.session().attribute("id_usuario") ;
		Map<String, Object> model = new HashMap<>();
		String empresa = req.params("empresa");
		String anio = req.params("anio");
		model.put("empresa", empresa);	
		model.put("anio", anio);
		
		Periodo periodo = obtenerPeriodo(empresa, anio);
		
		RepositorioIndicadores.instancia.filtrarPorUsuario(usuario);
		List<Indicador> indicadores =  RepositorioIndicadores.getIndicadores();
		model.put("indicadores" , obtenerIndicadoresValidos(indicadores,periodo));
		
		return new ModelAndView(model, "/indicadores/lindicadores.hbs");
		
	}
	
	private List<Indicador> obtenerIndicadoresValidos(List<Indicador> indicadores, Periodo periodo) {
		return listaFiltradaIndicadores(indicadores, periodo);
		
	}




	private List<Indicador> listaFiltradaIndicadores(List<Indicador> indicadores, Periodo periodo) {
		return indicadores.stream().filter(indicador -> esValido(indicador,periodo)).collect(Collectors.toList());
	}




	private boolean esValido(Indicador indicador, Periodo periodo) {
		try
		{
			RepositorioIndicadores.evaluarIndicador(indicador, periodo);
			return true;
		}
		catch (IndicadorException e) {
			return false;
		}
	}




	private Periodo obtenerPeriodo(String empresa, String anio) {
		return RepositorioEmpresas.obtenerPeriodo(empresa,anio);
	}
	
	
	/* pantalla de nuevo indicador */
	public ModelAndView indicadoresNuevo(Request req, Response res){
		
		Map<String,String> model = new HashMap<>();
		model.put("usuario", req.session().attribute("id_usuario").toString());
		model.put("mensaje", "");
		

		return new ModelAndView(model , "/indicadores/nuevo.hbs");
		
	}
	
	//creo nuevo indicador 
	public ModelAndView insertarIndicador(Request req , Response res){
		
		String nombre = req.queryParams("nombre");
		String formula= req.queryParams("formula");
		int usuario = Integer.parseInt(req.queryParams("usuario"));
		
		String mensaje= "";
		
		Indicador indicador = new Indicador(nombre,formula , usuario);
		
		if( !agregarIndicadorDB(indicador ) ) 
		{
			mensaje="Error al crear indicador";
		}	
		else{
			 mensaje="Indicador Creado";
		}
		
		Map<String,String> model = new HashMap<>();
		model.put("usuario", req.session().attribute("id_usuario").toString());
		model.put("mensaje", mensaje);
		
		return new ModelAndView(model , "/indicadores/nuevo.hbs");	
	}
	
	private boolean agregarIndicadorDB(Indicador indicador ){
		
		try {
			RepositorioIndicadores.agregarIndicador(indicador);
			ParserIndicadores.analizar(indicador.getFormula());
			GrabarIndicador.GrabarIndicador(indicador  );
			return true;
		} catch (Exception excepcion) {
			/*this.showWarning(excepcion.getMessage());*/
			return false;
		}
		
	}
}
