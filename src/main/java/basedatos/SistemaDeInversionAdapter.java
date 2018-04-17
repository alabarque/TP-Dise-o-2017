package basedatos;

import java.io.File;
import java.util.List;

import entidades.Empresa;
import entidades.Indicador;
import entidades.Metodologia;
import excepciones.ArchivoException;

public interface SistemaDeInversionAdapter {
	
	public List<Empresa> leerArchivoEmpresas(File archivo) 
			throws ArchivoException ;
	
	public List<Indicador> leerArchivoIndicador(File archivo)
			throws ArchivoException;
	
	public List<Metodologia> leerArchivoMetodologia(File archivo)
			throws ArchivoException;

}


