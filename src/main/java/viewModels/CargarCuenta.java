package viewModels;

import org.uqbar.commons.utils.Observable;

import entidades.Operador;
import excepciones.ArchivoException;
import excepciones.BDException;

@Observable
public class CargarCuenta {
	
	static String nombreArchivo;
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		CargarCuenta.nombreArchivo = nombreArchivo;
	}

	public static void cargarArchivoDeCuentas() throws NullPointerException,ArchivoException, BDException
	{
		Operador.cargarCuentas(nombreArchivo);
	}

}
