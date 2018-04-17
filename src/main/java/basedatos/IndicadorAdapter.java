package basedatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.codehaus.jackson.type.TypeReference;

import entidades.Empresa;
import entidades.Indicador;
import entidades.Metodologia;
import excepciones.ArchivoException;

public class IndicadorAdapter implements SistemaDeInversionAdapter {

	@Override
	// lee archivo y retorna lista de indicadores
	public List<Indicador> leerArchivoIndicador(File archivo)
			throws ArchivoException {

		ObjectMapper mapper = new ObjectMapper();
		List<Indicador> listaIndicadores = null;

		// Convert JSON string to Object
		try {
			listaIndicadores = mapper.readValue(new FileInputStream(archivo), new TypeReference<List<Indicador>>() {
			});
		} catch (JsonParseException e) {
			throw new ArchivoException("Error de Formato de Archivo");
		} catch (FileNotFoundException e) {
			throw new ArchivoException("NO Se Logro Cargar El Archivo");
		} catch (UnrecognizedPropertyException e) {
			throw new ArchivoException(
					"No esta bien formateado el archivo, le falta" + e.getLocation().getSourceRef().toString());
		} catch (JsonMappingException e) {
			throw new ArchivoException("NO se pudo Mapear los Indicadores");
		} catch (IOException e) {
			throw new ArchivoException("Error de Lectura/Escritura de Archivo");
		}

		return listaIndicadores;

	}

	@Override
	public List<Empresa> leerArchivoEmpresas(File archivo) throws ArchivoException {
		return null;
	}

	@Override
	public List<Metodologia> leerArchivoMetodologia(File archivo) throws ArchivoException {
		return null;
	}

}
