package entidades;

import java.io.File;
import java.util.List;



import basedatos.EmpresaAdapter;
import basedatos.GrabarEmpresas;
import basedatos.GrabarMetodologia;
import basedatos.IndicadorAdapter;
import basedatos.MetodologiaAdapter;
import excepciones.ArchivoException;
import excepciones.BDException;
import excepciones.IndicadorException;
import repositorios.RepositorioEmpresas;
import repositorios.RepositorioIndicadores;
import repositorios.RepositorioMetodologias;
import viewModels.PersonalizarMetodologia;
import viewModels.ConsultarCuenta;
import viewModels.ConsultarIndicador;
import viewModels.EvaluarMetodologiaPara1Empresa;

public class Operador {
	public static void consultarValorDeLasCuentas(ConsultarCuenta consultarcuenta) throws BDException  {

			consultarcuenta.consultarCuentas();

		
	}

	public static void cargarCuentas(String nombreArchivo)
			throws NullPointerException, ArchivoException, BDException {

		try {
			File file = new File(nombreArchivo);
			GrabarEmpresas.grabarEmpresas(file);

		} catch (NullPointerException excepcion) {
			throw new NullPointerException("Examine y elija un archivo para cargar");

		}
	}

	public static void cargarIndicadores(String nombreArchivo) throws NullPointerException, ArchivoException {
		try {
			File file = new File(nombreArchivo);
			IndicadorAdapter indicadorAdapter = new IndicadorAdapter();
			RepositorioIndicadores.agregarIndicadores(indicadorAdapter.leerArchivoIndicador(file));

		} catch (NullPointerException excepcion) {
			throw new NullPointerException("Examine y elija un archivo para cargar");

		}

	}

	public static void consultarValorDeLosIndicadores(ConsultarIndicador consultarIndicador) {
		try {
			consultarIndicador.setValor(
					consultarIndicador.getIndicadorSeleccionado().evaluar(consultarIndicador.getPeriodoSeleccionado()));
		} catch (NullPointerException excepcion) {
			throw new NullPointerException("Verifique que no haya campos vacios antes de consultar");

		} catch (IndicadorException e) {
			throw new NullPointerException("Hay problemas con el indicador");
		}
	}

	public static void cargarMetodologias(String nombreArchivo) throws NullPointerException, ArchivoException {
		try {
			File file = new File(nombreArchivo);
			MetodologiaAdapter metodologiaAdapter = new MetodologiaAdapter();
			RepositorioMetodologias.agregarMetodologias(metodologiaAdapter.leerArchivoMetodologia(file));

		} catch (NullPointerException excepcion) {
			throw new NullPointerException("Examine y elija un archivo para cargar");
		}

	}

	public static void crearMetodologia(Metodologia metodologia) {
		/* agrego al repositorio la metodologia y la agrego al archivo. */
		
		try {
			RepositorioMetodologias.agregarMetodologia(metodologia);
			GrabarMetodologia.persistir(metodologia);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
		
	}

	public static void agregarCondicionALaMetodologia(PersonalizarMetodologia modelObject) {
		// TODO Auto-generated method stub
		
	}

}
