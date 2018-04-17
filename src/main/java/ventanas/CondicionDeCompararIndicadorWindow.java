package ventanas;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import entidades.Empresa;
import entidades.Indicador;
import entidades.Metodologia;
import excepciones.MetodologiaException;
import viewModels.CondicionDeCompararIndicador;

public class CondicionDeCompararIndicadorWindow extends SimpleWindow<CondicionDeCompararIndicador> {

	public CondicionDeCompararIndicadorWindow(WindowOwner parent, Metodologia metodologia) {
		super(parent, new CondicionDeCompararIndicador());
		this.getModelObject().setMetodologia(metodologia);
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Agregar Condicion a la Metodologia").onClick(this::agregarCondicion);
		new Button(actionsPanel).setCaption("Cancelar").onClick(this::cancelar).setWidth(260);
	}
		


	@Override
	protected void createFormPanel(Panel mainPanel) {

		this.setTitle("Agregar Condicion de Comparacion de Indicador");
		
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
		new Label(form).setText("Empresa");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form);
		selectorEmpresa.setWidth(250);
		selectorEmpresa.bindItemsToProperty("empresas");
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		
		new Label(form).setText("Indicador");
		Selector<Indicador> selectorIndicador = new Selector<Indicador>(form);
		selectorIndicador.bindItemsToProperty("indicadores");
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		
		new Label(form).setText("el indicador a comparar debe ser:");
		Selector<String> selectorComparador = new Selector<String>(form);
		selectorComparador.bindItemsToProperty("comparadores");
		selectorComparador.bindValueToProperty("comparadorSeleccionado");
	}
	
	protected void agregarCondicion(){
		try {
			this.getModelObject().agregarCondicion();
			SimpleWindow<?> window = new PersonalizarMetodologiaWindow(this, this.getModelObject().getMetodologia());
			this.close();
			window.open();
		} catch (MetodologiaException e) {
			this.showWarning(e.getMessage());
			e.printStackTrace();
		}

	}
	
	protected void cancelar(){
		SimpleWindow<?> window = new PersonalizarMetodologiaWindow(this, this.getModelObject().getMetodologia());
		this.close();
		window.open();
	}
}
