package controllers;

import entidades.PasswordHasher;
import entidades.Usuario;
import excepciones.BDException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public class LoginController {
	
	
	public ModelAndView login(Request req, Response res){
		
		return new ModelAndView(null, "login.html");
	}
	
	public String antiHacker(Request req, Response res){
		
		 if (skynetChecker(req))
			res.redirect("/skynet.html");
		 
		return null;
	
	}

	private boolean skynetChecker(Request req) {
		return req.session().attribute("id_usuario") == null && !req.pathInfo().matches("/(login|logout|signup|registrado)?");
	}
	
	
	public String logout(Request req, Response res){
		req.session().removeAttribute("id_usuario");
		res.redirect("/login");
		return null;
	}
	
	
	public ModelAndView loginUsuario(Request req, Response res){
		
		String usuario = req.queryParams("usuario");
		String password= req.queryParams("password");
		
		Usuario login = new Usuario(usuario, password);
		
		try {
			Usuario user = validarUsuario(login);

			req.session(true).attribute("id_usuario" , user.getUsuarioId() );
			return new ModelAndView(null, "SI.hbs");
		} catch (BDException e) {
			res.redirect("/login");
			return null;
		}
	}


	private Usuario validarUsuario(Usuario login) throws BDException {

		return login.autorizacion();

		
	}

}
