package ventanas;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import entidades.Metodologia;
import viewModels.CondicionDeLongevidad;

public class CondicionDeLongevidadWindow extends SimpleWindow<CondicionDeLongevidad> {

	public CondicionDeLongevidadWindow(WindowOwner owner, Metodologia metodologia) {
		super(owner, new CondicionDeLongevidad());
		this.getModelObject().setMetodologia(metodologia);
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Agregar Condicion de Longevidad");
		
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
		new Label(form).setText("Seleccionar anio");
		new NumericField(form).bindValueToProperty("anio");
		
		
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Agregar Condicion de Longevidad").onClick(this::agregarCondicion);
		new Button(actionsPanel).setCaption("cancelar").onClick(this::cancelar).setWidth(260);
	}
	
	protected void agregarCondicion(){
		this.getModelObject().agregarCondicion();
		SimpleWindow<?> window = new PersonalizarMetodologiaWindow(this, this.getModelObject().getMetodologia());
		this.close();
		window.open();
	}
	
	protected void cancelar(){
		SimpleWindow<?> window = new PersonalizarMetodologiaWindow(this, this.getModelObject().getMetodologia());
		this.close();
		window.open();
	}
	
}
