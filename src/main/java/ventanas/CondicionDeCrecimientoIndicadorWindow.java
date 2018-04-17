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
import viewModels.CondicionDeCrecimientoIndicador;

public class CondicionDeCrecimientoIndicadorWindow extends SimpleWindow<CondicionDeCrecimientoIndicador> {

	public CondicionDeCrecimientoIndicadorWindow(WindowOwner parent, Metodologia metodologia) {
		super(parent, new CondicionDeCrecimientoIndicador());
		this.getModelObject().setMetodologia(metodologia);
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Agregar Condicion a la Metodologia").onClick(this::agregarCondicion);
		new Button(actionsPanel).setCaption("Cancelar").onClick(this::cancelar).setWidth(260);
	}
		


	@Override
	protected void createFormPanel(Panel mainPanel) {

		this.setTitle("Agregar Condicion de Crecimiento de Indicador");
		
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
		
		new Label(form).setText("intervalo de anios");
		new NumericField(form).bindValueToProperty("intervalo");
		
		new Label(form).setText("Indicador");
		Selector<Indicador> selectorIndicador = new Selector<Indicador>(form);
		selectorIndicador.bindItemsToProperty("indicadores");
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		
		new Label(form).setText("Tipo de crecimiento del indicador:");
		Selector<String> selectorComparador = new Selector<String>(form);
		selectorComparador.bindItemsToProperty("ordenes");
		selectorComparador.bindValueToProperty("ordenSeleccionado");
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
