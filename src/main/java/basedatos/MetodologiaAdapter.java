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

public class MetodologiaAdapter implements SistemaDeInversionAdapter {

	@Override
	public List<Empresa> leerArchivoEmpresas(File archivo) throws ArchivoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Indicador> leerArchivoIndicador(File archivo) throws ArchivoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Metodologia> leerArchivoMetodologia(File archivo) throws ArchivoException {

		ObjectMapper mapper = new ObjectMapper();
		List<Metodologia> listaMetodologias = null;

		// Convert JSON string to Object
		try {
			listaMetodologias = mapper.readValue(new FileInputStream(archivo), new TypeReference<List<Metodologia>>() {
			});
		} catch (JsonParseException e) {
			throw new ArchivoException("Error de Formato de Archivo");
		} catch (FileNotFoundException e) {
			throw new ArchivoException("NO Se Logro Cargar El Archivo");
		} catch (UnrecognizedPropertyException e) {
			throw new ArchivoException(
					"No esta bien formateado el archivo, le falta" + e.getLocation().getSourceRef().toString());
		} catch (JsonMappingException e) {
			throw new ArchivoException("NO se pudo Mapear las Metodologias");
		} catch (IOException e) {
			throw new ArchivoException("Error de Lectura/Escritura de Archivo");
		}

		return listaMetodologias;
	}

}
