package ventanas;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import entidades.Metodologia;
import excepciones.MetodologiaException;
import viewModels.ConsultarCuenta;
import viewModels.CrearMetodologia;

public class CrearMetodologiaWindow extends SimpleWindow<CrearMetodologia> {

	public CrearMetodologiaWindow(WindowOwner parent) {
		super(parent,  new CrearMetodologia());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Crear Metodologia");
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
		new Label(form).setText("Nombre de la metodologia");
		new TextBox(form).setWidth(300).bindValueToProperty("nombre");
		
		
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Agregar Condiciones a la Metodologia").onClick(this::personalizarMetodologia).setAsDefault();
		new Button(actionsPanel).setCaption("Crear Metodologia Buffet").onClick(this::buffet).setWidth(260);
		new Button(actionsPanel).setCaption("cancelar").onClick(this::cerrar);
	}
	
	protected void personalizarMetodologia(){
		SimpleWindow<?> window;
		try {
			window = new PersonalizarMetodologiaWindow(this, this.getModelObject().crearMetodologia());
			window.open();
			this.close();
		} catch (MetodologiaException e) {
			this.showWarning(e.getMessage());
			e.printStackTrace();
		}
	}
	
	protected void buffet(){
		SimpleWindow<?> window;
		try {
			window = new MetodologiaBuffetWindow(this, this.getModelObject().crearMetodologiaBuffet());
			window.open();
			this.close();
		} catch (MetodologiaException e) {
			this.showWarning(e.getMessage());
			e.printStackTrace();
		}
	}
	
	protected void cerrar(){
		this.close();
	}
}
