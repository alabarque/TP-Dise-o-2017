package parserTest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import basedatos.IndicadorAdapter;
import entidades.Indicador;
import entidades.Periodo;
import excepciones.IndicadorException;
import parser.ParserIndicadores;

public class ParserIndicadoresTest {
	List<Indicador> listaIndicadores = new ArrayList<Indicador>();
	Periodo periodo1 = new Periodo();
	Periodo periodo2 = new Periodo();
	Periodo periodo3 = new Periodo();
	Periodo periodo4 = new Periodo();
	Periodo periodo5 = new Periodo();
	Periodo periodo6 = new Periodo();
	
	@Before
    public void setUp() throws Exception {
		IndicadorAdapter adapter = new IndicadorAdapter();
		listaIndicadores = adapter.leerArchivoIndicador(new File("indicadores.json"));
		periodo1.setPeriodo(2017);
		periodo2.setPeriodo(2018);
		periodo3.setPeriodo(2019);
		periodo4.setPeriodo(2016);
		periodo5.setPeriodo(2015);
		periodo6.setPeriodo(2012);
		
	}
	
	@Test(expected=IndicadorException.class)
	public void indicadorInvalidoTest() throws IndicadorException  {
	 	ParserIndicadores.analizar("i");
	}
	
	@Test(expected=IndicadorException.class)
	public void indicadorInvalido2Test() throws IndicadorException  {
	 	ParserIndicadores.analizar("c");
	}
	
	@Test(expected=IndicadorException.class)
	public void indicadorInvalido3Test() throws IndicadorException  {
	 	ParserIndicadores.analizar("3+");
	}
	
	@Test(expected=IndicadorException.class)
	public void indicadorInvalido4Test() throws IndicadorException  {
	 	ParserIndicadores.analizar("562.");
	}
	
	@Test(expected=IndicadorException.class)
	public void indicadorInvalido5Test() throws IndicadorException  {
	 	ParserIndicadores.analizar("c45464");
	}
	
	@Test(expected=IndicadorException.class)
	public void indicadorInvalido6Test() throws IndicadorException  {
	 	ParserIndicadores.analizar("i46466");
	}
	
	@Test
	public void evaluarIndicadorTest() throws IndicadorException  {
	 	assertEquals(0, ParserIndicadores.evaluar("iINDCADOR1", periodo2),200);
	}
	
	@Test(expected=NullPointerException.class)
	public void evaluarCuentaTest() throws IndicadorException  {
	 	assertEquals(ParserIndicadores.evaluar("cTDA", periodo1),0,0);
	}
	
	@Test(expected=NullPointerException.class)
	public void evaluarExpresionTest() throws IndicadorException  {
	 	assertEquals(ParserIndicadores.evaluar("cTDA+iIndicador", periodo3), 4035,0);
	}

	@Test
	public void evaluarConstanteTest() throws IndicadorException  {
	 	assertEquals(2, ParserIndicadores.evaluar("2", periodo5),0);
	}
	
	@Test
	public void evaluarConstante2Test() throws IndicadorException  {
	 	assertEquals(23.50, ParserIndicadores.evaluar("23.50", periodo6),0);
	}

}
