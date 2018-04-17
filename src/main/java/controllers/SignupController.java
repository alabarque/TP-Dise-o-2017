package controllers;

import java.util.HashMap;
import java.util.Map;

import basedatos.CrearUsuario;
import entidades.PasswordHasher;
import entidades.Usuario;
import excepciones.BDException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class SignupController {

	public ModelAndView nuevoUsuario(Request req, Response res){
		return new ModelAndView(null, "signup.html");
	}
	
	public ModelAndView crearUsuario(Request req, Response res) throws BDException{
		String nombreUsuario = req.queryParams("usuario");
		String contrasenia = req.queryParams("contrasenia");
		String verificacionContrasenia = req.queryParams("verificacioncontrasenia");
		String mensajeContrasenia = "las contrasenias no coinciden";
		String mensajeCreacionUsuario = "";
		
		PasswordHasher passHasher = null;
		String passHasheada = passHasher.hashPassword(contrasenia);
		Usuario usuario = new Usuario(nombreUsuario, passHasheada);
		
		Map<String,String> model = new HashMap<>();
		
		if(!contrasenia.equals(verificacionContrasenia)){
			model.put("mensajeContrasenia", mensajeContrasenia);
		}else if(usuario.usuarioNoEstaRepetido()){
			CrearUsuario.GrabarUsuario(usuario);
			res.redirect("/registrado");
			return null;
		}else {
			mensajeCreacionUsuario = "el nombre de usuario ya esta en uso";
		}
		model.put("mensajeCreacionUsuario", mensajeCreacionUsuario);
		return new ModelAndView(model, "signup.html");		
	}
	
	public ModelAndView registrado(Request req, Response res){
		return new ModelAndView(null, "registroexitoso.html");
	}
}
