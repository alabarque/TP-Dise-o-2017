package ventanas;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import entidades.Indicador;
import entidades.Metodologia;
import excepciones.MetodologiaException;
import operacionDeCondiciones.Operacion;
import viewModels.CondicionDeEvaluarPeriodoNAnios;

public class CondicionDeEvaluarPeriodoNAniosWindow extends SimpleWindow<CondicionDeEvaluarPeriodoNAnios> {

	public CondicionDeEvaluarPeriodoNAniosWindow(WindowOwner parent, Metodologia metodologia) {
		super(parent, new CondicionDeEvaluarPeriodoNAnios());
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
		
		
		new Label(form).setText("Intervalo de anios");
		new NumericField(form).bindValueToProperty("intervalo");
		
		new Label(form).setText("Valor a comparar");
		new NumericField(form).bindValueToProperty("valor");
		
		new Label(form).setText("Indicador");
		Selector<Indicador> selectorIndicador = new Selector<Indicador>(form);
		selectorIndicador.bindItemsToProperty("indicadores");
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		
		new Label(form).setText("Como debe ser el indicador respecto del valor");
		Selector<String> selectorComparador = new Selector<String>(form);
		selectorComparador.bindItemsToProperty("comparadores");
		selectorComparador.bindValueToProperty("comparadorSeleccionado");
		
		new Label(form).setText("seleccione un tipo de operacion");
		new Label(form).setText("(elegir 'ninguna operacion' si se quiere evaluar sin operar los indicadores)");
		Selector<Operacion> selectorOperacion = new Selector<Operacion>(form);
		selectorOperacion.bindItemsToProperty("operaciones");
		selectorOperacion.bindValueToProperty("operacionSeleccionada");
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
