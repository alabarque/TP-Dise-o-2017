package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.Cuenta;
import entidades.Periodo;
import entidades.RCuentas;
import entidades.RIndicadores;
import excepciones.IndicadorException;
import repositorios.RepositorioEmpresas;
import repositorios.RepositorioIndicadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RIndicadoresController {
	
	//get para evaluar indicadores
	public ModelAndView evaluarIndicador( Request req , Response resp ) throws IndicadorException {
		
		String empresa = req.params("empresa");
		String anio= req.params("anio");
		String indicador= req.params("indicador");
		Double valor = 0.0;
		
		Periodo periodo = obtenerPeriodo(empresa, anio);
		valor = RepositorioIndicadores.evaluarIndicador(indicador, periodo);
		
		Map<String, RIndicadores> modelrindicadores = new HashMap<>();
		
		
		
		RIndicadores rindicadores =	new RIndicadores(empresa,anio,indicador,valor);
		modelrindicadores.put("rindicadores", rindicadores);
		
		
		return new ModelAndView(modelrindicadores, "/indicadores/rindicadores.hbs");
		
	}
	
	private Periodo obtenerPeriodo(String empresa, String anio) {
		return RepositorioEmpresas.obtenerPeriodo(empresa,anio);
	}

}
