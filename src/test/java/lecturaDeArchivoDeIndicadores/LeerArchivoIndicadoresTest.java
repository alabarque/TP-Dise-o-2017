package lecturaDeArchivoDeIndicadores;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import basedatos.EmpresaAdapter;
import basedatos.IndicadorAdapter;
import entidades.Empresa;
import entidades.Indicador;
import entidades.Periodo;
import excepciones.IndicadorException;
import parser.ParserIndicadores;

public class LeerArchivoIndicadoresTest {
	List<Indicador> listaIndicadores = new ArrayList<Indicador>();
	List<Empresa> listaEmpresas = new ArrayList<Empresa>();
	Periodo periodoTest = new Periodo();
	Periodo periodo2Facebook = new Periodo();

	@Before
	public void setUp() throws Exception {
		EmpresaAdapter empresaAdapter = new EmpresaAdapter();
		IndicadorAdapter indicadorAdapter = new IndicadorAdapter();
		listaIndicadores = indicadorAdapter.leerArchivoIndicador(new File("indicadores.json"));
		listaEmpresas = empresaAdapter.leerArchivoEmpresas(new File("empresas.json"));
		periodoTest.setPeriodo(2017);
		periodo2Facebook = listaEmpresas.get(0).getPeriodos().get(1);
	}

	@Test
	public void elDecimoIndicadorDa666Test() throws IndicadorException {
		assertEquals(666, ParserIndicadores.evaluar(listaIndicadores.get(9).getFormula(), periodoTest), 0);
	}

	@Test
	public void elTercerIndicadorDaTest() throws IndicadorException {
		assertEquals(114.84, ParserIndicadores.evaluar(listaIndicadores.get(3).getFormula(), periodo2Facebook), 0);

	}
	
	@Test 
	public void elIndicadorINDICADOR1ValeTest() throws IndicadorException{
		
		assertEquals(200, ParserIndicadores.evaluar(listaIndicadores.get(1).getFormula(), periodoTest), 0 );
	}

}
