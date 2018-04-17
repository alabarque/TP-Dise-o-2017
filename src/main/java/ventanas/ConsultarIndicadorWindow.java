package ventanas;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import entidades.Empresa;
import entidades.Indicador;
import entidades.Operador;
import entidades.Periodo;
import viewModels.ConsultarIndicador;

public class ConsultarIndicadorWindow extends SimpleWindow<ConsultarIndicador>{

	public ConsultarIndicadorWindow(WindowOwner parent) {
		super(parent, new ConsultarIndicador());
		
	}
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultar Indicador");
		
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
		new Label(form).setText("Empresa");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form);
		selectorEmpresa.setWidth(250);
		selectorEmpresa.bindItemsToProperty("empresas");
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		
		
		new Label(form).setText("Anio");
		Selector<Periodo> selectorPeriodos = new Selector<Periodo>(form);
		selectorPeriodos.setWidth(250);
		selectorPeriodos.bindItemsToProperty("empresaSeleccionada.periodos");
		selectorPeriodos.bindValueToProperty("periodoSeleccionado");
		
		
		
		new Label(form).setText("Indicador");
		Selector<Indicador> selectorIndicador = new Selector<Indicador>(form);
		selectorIndicador.setWidth(250);
		selectorIndicador.bindItemsToProperty("indicadores");
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		

		new Label(form).setText("Valor").bindValueToProperty("valor");
		
		/*
		Table<Indicador> tableIndicador = new Table<Indicador>(form, Indicador.class);
		tableIndicador.setNumberVisibleRows(2);
		tableIndicador.bindItemsToProperty("indicadorSeleccionado");
		tableIndicador.bindValueToProperty("indicadorSeleccionado");
		
		Column<Indicador> columnaID = new Column<Indicador>(tableIndicador);
		columnaID.setFixedSize(120);
		columnaID.setTitle("Formula");
		columnaID.bindContentsToProperty("formula");
		
		Column<Indicador> columnaNombre = new Column<Indicador>(tableIndicador);
		columnaNombre.setFixedSize(120);
		columnaNombre.setTitle("Valor");
		columnaNombre.bindContentsToProperty("valor");	
		*/
		
		
		new Button(form).setCaption("Consultar Indicador").onClick(this::Consultar).setWidth(250);
		
		
		
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		
		
	}
	
	protected void Consultar(){
		
		try {
			Operador.consultarValorDeLosIndicadores(this.getModelObject());
		} catch (Exception e) {
			e.printStackTrace();
			this.showWarning(e.getMessage());
		}
	}
	


	

}
