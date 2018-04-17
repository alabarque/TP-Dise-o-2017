package controllers;


import java.util.HashMap;
import java.util.Map;

import repositorios.RepositorioEmpresas;
import repositorios.RepositorioIndicadores;
import repositorios.RepositorioMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController {

	public ModelAndView metodologias(Request req, Response res){
		
		//obtengo usuario
		int usuario = req.session().attribute("id_usuario") ;
		
		//cargo al repo las metodologias
		RepositorioMetodologias.instancia.obtenerMetodologias();
		
		/*
		 * crear hashmap y completar con la BdD
		 */
		Map<String, Object> model = new HashMap<>();
		model.put("usuario", usuario);
		model.put("empresas", RepositorioEmpresas.getEmpresas());
		model.put("metodologias", RepositorioMetodologias.getMetodologias());
				
		return new ModelAndView(model, "metodologias/metodologias.hbs");
	}
}
