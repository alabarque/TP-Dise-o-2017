package basedatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.codehaus.jackson.type.TypeReference;

import org.hibernate.Session;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import entidades.Empresa;
import entidades.Indicador;
import entidades.Metodologia;
import excepciones.ArchivoException;
import excepciones.BDException;

public class EmpresaAdapter implements SistemaDeInversionAdapter {

	// // crea el archivo en disco, recibe como parametro la lista de empresas
	// public static void crearArchivo(List<Empresa> listaEmpresas) throws
	// JsonGenerationException, JsonMappingException, IOException {
	//
	// ObjectMapper mapper = new ObjectMapper();
	// ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
	//
	// //Convert object to JSON string and save into file directly
	// writer.writeValue(new File("empresas.json"), listaEmpresas);
	//
	// }

	// lee el archivo en disco, retorna la lista de empresas
	public List<Empresa> leerArchivoEmpresas(File archivo)
			throws ArchivoException {

		ObjectMapper mapper = new ObjectMapper();
		List<Empresa> listaEmpresas = null;

		// Convert JSON string to Object
		try {
			listaEmpresas = mapper.readValue(new FileInputStream(archivo), new TypeReference<List<Empresa>>() {
			});
		} catch (JsonParseException e) {
			throw new ArchivoException("Error de Formato de Archivo");
		} catch (FileNotFoundException e) {
			throw new ArchivoException("NO Se Logro Cargar El Archivo");
		} catch (UnrecognizedPropertyException e) {
			throw new ArchivoException(
					"No esta bien formateado el archivo, le falta" + e.getLocation().getSourceRef().toString());
		} catch (JsonMappingException e) {
			throw new ArchivoException("NO se pudo Mapear las Empresas");
		} catch (IOException e) {
			throw new ArchivoException("Error de Lectura/Escritura de Archivo");
		}


		return listaEmpresas;

	}

	@Override
	public List<Indicador> leerArchivoIndicador(File archivo) throws ArchivoException {
		return null;
	}

	@Override
	public List<Metodologia> leerArchivoMetodologia(File archivo) throws ArchivoException {
		return null;
	}

}
