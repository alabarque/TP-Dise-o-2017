package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import entidades.Empresa;
import entidades.Metodologia;
import entidades.RMetodologias;
import entidades.Resultado;
import repositorios.RepositorioEmpresas;
import repositorios.RepositorioMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RMetodologiasController {

	public ModelAndView resultados(Request req, Response res)
	{
		String empresaNombre = req.params("empresa");
		Integer anio = Integer.parseInt(req.params("anio"));
		String metodologiaNombre = req.params("metodologia");
		List<Resultado> resultados = new ArrayList<Resultado>();
		
		Empresa empresa = obtenerEmpresa(empresaNombre);
		Metodologia metodologia = obtenerMetodologia(metodologiaNombre);
		resultados = metodologia.evaluarEmpresa(anio, empresa);
		
		RMetodologias rmetodologias = new RMetodologias(empresa, anio, metodologia, resultados);
		


		Map<String, RMetodologias> modelrmetodologias = new HashMap<>();
		modelrmetodologias.put("rmetodologias", rmetodologias);
		
		return new ModelAndView(modelrmetodologias, "/metodologias/rmetodologias.hbs");

	}
	
	private Empresa obtenerEmpresa(String empresa){
		return RepositorioEmpresas.obtenerEmpresa(empresa);
	}
	
	private Metodologia obtenerMetodologia(String metodologia){
		return RepositorioMetodologias.obtenerMetodologia(metodologia);
	}
}
