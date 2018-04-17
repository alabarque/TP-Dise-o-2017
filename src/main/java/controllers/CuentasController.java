package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.QueryHints;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import entidades.Cuenta;
import entidades.Empresa;
import entidades.Periodo;
import repositorios.RepositorioEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController {

	public ModelAndView cuentas(Request req, Response res) {
		
		Map<String, List<Empresa>> model = new HashMap<>();

		model.put("empresas", RepositorioEmpresas.getEmpresas());

		return new ModelAndView(model, "cuentas/cuentas.hbs");

	
	}
	
	
	public ModelAndView consultar(Request req, Response res) {
		
		Map<String, Object> model = new HashMap<>();
		
		String empresa = req.params("empresa");
		List<Periodo> periodos = RepositorioEmpresas.obtenerEmpresa(empresa).getPeriodos();
		
		model.put("empresa",empresa);
		model.put("periodos", periodos);
		return new ModelAndView(model, "cuentas/consultar.hbs");

	
	}
	
	
	
	
}
