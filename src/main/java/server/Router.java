package server;

import java.io.IOException;

import controllers.CuentasController;
import controllers.IndicadoresController;
import controllers.LoginController;
import controllers.MetodologiasController;
import controllers.RCuentasController;
import controllers.RIndicadoresController;
import controllers.RMetodologiasController;
import controllers.SignupController;
import controllers.HomeController;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;
import spark.utils.IOUtils;

public class Router {


	public static void configure() {
		HandlebarsTemplateEngine engine= HandlebarsTemplateEngineBuilder.create().build(); 

		Spark.staticFiles.location("/public");
		
		HomeController homeController = new HomeController();
		IndicadoresController indicadorController = new IndicadoresController();
		CuentasController cuentaController = new CuentasController();
		MetodologiasController metodologiasController = new MetodologiasController();
		LoginController loginController = new LoginController();
		SignupController signupController = new SignupController();
		RCuentasController rCuentasController = new RCuentasController();
		RIndicadoresController rIndicadoresController = new RIndicadoresController();
		RMetodologiasController rMetodologiasController = new RMetodologiasController();
		
		Spark.before("/*",loginController::antiHacker);
		
		Spark.get("/home", homeController::home, engine);
		Spark.get("/", loginController::login, engine);
		Spark.get("/login", loginController::login, engine);
		Spark.get("/logout", loginController::logout);
		Spark.post("/login",loginController::loginUsuario,engine);
		Spark.get("/cuentas", cuentaController::cuentas, engine);
		Spark.get("/cuentas/:empresa/", cuentaController::consultar, engine);
		Spark.get("/cuentas/:empresa/:anio/", rCuentasController::resultados,engine);
		Spark.get("/indicadores", indicadorController::indicadores , engine);
		Spark.get("/indicadores/:empresa/",indicadorController::consultar , engine);
		Spark.get("/indicadores/:empresa/:anio/",indicadorController::listarIndicadores , engine);
		Spark.get("/indicadores/:empresa/:anio/:indicador/", rIndicadoresController::evaluarIndicador , engine);
		Spark.get("/indicadores/nuevo", indicadorController::indicadoresNuevo, engine);
		Spark.post("/indicadores/nuevo", indicadorController::insertarIndicador , engine);
		Spark.get("/metodologias", metodologiasController::metodologias, engine);
		Spark.get("/metodologias/:metodologia/:anio/:empresa", rMetodologiasController::resultados, engine);
		Spark.get("/signup", signupController::nuevoUsuario, engine);
		Spark.post("/signup", signupController::crearUsuario, engine);
		Spark.get("/registrado", signupController::registrado, engine);
	}
	
	
	/*public static String home(Request request,Response response) throws IOException
	{
		response.redirect("/SI");
		resultado = "";
		return resultado;
	}	
	
	public static String sistemaInversiones(Request request,Response response) throws IOException
	{
		resultado = IOUtils.toString(Spark.class.getResourceAsStream("/public/SI.html"));
		return resultado;
	}	
	
	public static String cuentas(Request request,Response response) throws IOException
	{
		resultado = IOUtils.toString(Spark.class.getResourceAsStream("/public/cuentas.html"));
		return resultado;
	}	
	
	public static String indicadores(Request request,Response response) throws IOException
	{
		resultado = IOUtils.toString(Spark.class.getResourceAsStream("/public/indicadores.html"));
		return resultado;
	}
		
	public static String login(Request request,Response response) throws IOException
	{
		String usuario = request.queryParams("user");
		String password = request.queryParams("password");
				
		try
		{
			if(!usuario.isEmpty() && !password.isEmpty())
			{
			response.redirect("/SI");
			resultado="";
			}
			else
			{
				resultado = IOUtils.toString(Spark.class.getResourceAsStream("/public/login.html"));	
			}
		}
		catch (NullPointerException e) {
			resultado = IOUtils.toString(Spark.class.getResourceAsStream("/public/login.html"));
		}

		return resultado;
	}*/

	
}
