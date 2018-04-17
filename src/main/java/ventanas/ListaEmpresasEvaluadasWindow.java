package ventanas;


import java.util.ArrayList;
import java.util.List;


import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import entidades.Metodologia;
import entidades.Resultado;
import viewModels.ListaEmpresasEvaluadas;

public class ListaEmpresasEvaluadasWindow extends SimpleWindow<ListaEmpresasEvaluadas> {
	
	public ListaEmpresasEvaluadasWindow(WindowOwner parent, Metodologia metodologia, Integer anio){
		super(parent, new ListaEmpresasEvaluadas());
		this.getModelObject().setMetodologia(metodologia);
		this.getModelObject().setAnio(anio);
	}
	
	
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Evaluar Metodologia para todas las empresas");
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
		
		Table<Resultado> tablaResultados = new Table<Resultado>(form, Resultado.class);
		
		tablaResultados.setNumberVisibleRows(15);
		tablaResultados.bindItemsToProperty("resultados");
		
		Column<Resultado> columnaEmpresa = new Column<Resultado>(tablaResultados);
		columnaEmpresa.setFixedSize(120);
		columnaEmpresa.setTitle("Nombre");
		columnaEmpresa.bindContentsToProperty("nombreEmpresa");
		
		Column<Resultado> columnaCondicion = new Column<Resultado>(tablaResultados);
		columnaCondicion.setFixedSize(400);
		columnaCondicion.setTitle("Condicion");
		columnaCondicion.bindContentsToProperty("nombreCondicion");	
		
		Column<Resultado> columnaResultado = new Column<Resultado>(tablaResultados);
		columnaResultado.setFixedSize(120);
		columnaResultado.setTitle("Pasa la Metodologia");
		columnaResultado.bindContentsToProperty("resultado");	
		
	}


	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Consultar").onClick(this::mostrarResultados).setWidth(260);
		
	}
	
	public void mostrarResultados(){
		this.getModelObject().crearListaDeResultados();
	}

}
