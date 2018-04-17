package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.Cuenta;
import entidades.Empresa;
import entidades.Periodo;
import entidades.RCuentas;
import repositorios.RepositorioEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RCuentasController {

	public ModelAndView resultados(Request req, Response res)
	{
		String empresa = req.params("empresa");
		String anio= req.params("anio");
		RCuentas rcuentas =	new RCuentas(empresa, anio,obtenerCuentas(empresa, anio));

		

		Map<String, RCuentas> modelrcuentas = new HashMap<>();
		modelrcuentas.put("rcuentas", rcuentas);
		return new ModelAndView(modelrcuentas, "/cuentas/rcuentas.hbs");
		
	}

	private List<Cuenta> obtenerCuentas(String empresa, String anio) {
		return RepositorioEmpresas.obtenerPeriodo(empresa,anio).getCuentas();
	}

}
