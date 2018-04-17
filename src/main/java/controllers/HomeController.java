package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

	
	public ModelAndView home(Request req, Response res){
		
		return new ModelAndView(null, "SI.hbs");
	}
	

	
}
