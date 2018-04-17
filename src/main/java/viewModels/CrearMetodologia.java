package viewModels;

import org.uqbar.commons.utils.Observable;

import condicionesImpuestasPorElUser.MetodologiaBuffet;
import entidades.Metodologia;
import excepciones.MetodologiaException;



@Observable
public class CrearMetodologia {
	
	//atributos
	private String nombre ;

	
	//metodos
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//constructor 
	public Metodologia crearMetodologia() throws MetodologiaException
	{
		if (this.nombre == null){
			throw new MetodologiaException("los campos no pueden quedar vacio");
		}
		return new Metodologia(this.nombre);
	}
	
	public MetodologiaBuffet crearMetodologiaBuffet() throws MetodologiaException{
		if (this.nombre == null){
			throw new MetodologiaException("los campos no pueden quedar vacio");
		}
			return new MetodologiaBuffet(nombre);
	}


	

}
