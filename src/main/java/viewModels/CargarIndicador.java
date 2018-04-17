package viewModels;

import org.uqbar.commons.utils.Observable;

import entidades.Operador;
import excepciones.ArchivoException;

@Observable
public class CargarIndicador {
	static String nombreArchivo;

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		CargarIndicador.nombreArchivo = nombreArchivo;
	}

	public static void cargarArchivoDeIndicadores() throws NullPointerException, ArchivoException {
		Operador.cargarIndicadores(nombreArchivo);
	}
}
