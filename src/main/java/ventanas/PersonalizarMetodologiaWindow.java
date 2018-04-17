package ventanas;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import entidades.Condicion;
import entidades.Empresa;
import entidades.Metodologia;
import entidades.Operador;
import viewModels.PersonalizarMetodologia;
import viewModels.CrearMetodologia;

public class PersonalizarMetodologiaWindow extends SimpleWindow<PersonalizarMetodologia>{
	
	public PersonalizarMetodologiaWindow(WindowOwner parent, Metodologia metodologia){
		super(parent, new PersonalizarMetodologia());
		this.getModelObject().setMetodologia(metodologia);
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Agregar Condicion");
		
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
	}

	@Override
	protected void addActions(Panel actionsPanel) {

		new Button(actionsPanel).setCaption("Agregar Condicion").onClick(this::agregarCondicion).setWidth(260);
		new Button(actionsPanel).setCaption("Crear Metodologia").onClick(this::crearMetodologia).setWidth(260);
		new Button(actionsPanel).setCaption("Cancelar").onClick(this::cancelar).setWidth(260);
	}
	
	protected void crearMetodologia(){
		//chequeo si 
		if(this.getModelObject().noTieneCondiciones()){
			this.showError("Agregue al menos una condicion a la metodologia");
		} else {
			Operador.crearMetodologia
				(this.getModelObject().getMetodologia());
			this.showInfo("Metodologia Creada");
			this.close();
		}
	}
	
	protected void agregarCondicion(){
		SimpleWindow<?> window = new TipoDeCondicionWindow(this, this.getModelObject().getMetodologia());
		window.open();
		this.close();
	}
	
	protected void cancelar(){
		this.close();
	}
	

}
