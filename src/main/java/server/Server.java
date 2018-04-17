package server;
import java.util.ArrayList;
import java.util.List;

import basedatos.GrabarMetodologia;
import condicionesImpuestasPorElUser.CondicionCrecimientoIndicador;
import entidades.Condicion;
import entidades.Metodologia;
import excepciones.BDException;
import operacionDeCondiciones.Orden;
import repositorios.RepositorioEmpresas;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server{
	
	public static void main(String[] args) throws BDException {
//		CondicionCrecimientoIndicador condicion = new CondicionCrecimientoIndicador(2, "INDCADOR1", Orden.CRECIENTE);
//		List<Condicion> condiciones = new ArrayList<Condicion>();
//		condiciones.add(condicion);
//		Metodologia metodologia = new Metodologia("pruebaMetodologia2", condiciones);
//		GrabarMetodologia.persistir(metodologia);
		
		RepositorioEmpresas.instancia.listaEmpresas();
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
}
