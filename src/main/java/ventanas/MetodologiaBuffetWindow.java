package ventanas;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import condicionesImpuestasPorElUser.MetodologiaBuffet;
import entidades.Empresa;
import entidades.Operador;
import viewModels.MetodologiaBuffetVM;

public class MetodologiaBuffetWindow extends SimpleWindow<MetodologiaBuffetVM> {

	public MetodologiaBuffetWindow(WindowOwner parent, MetodologiaBuffet metodologia) {
		super(parent, new MetodologiaBuffetVM());
		this.getModelObject().setMetodologiaBuffet(metodologia);
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Crear Metodologia Buffet").onClick(this::crear);
		new Button(actionsPanel).setCaption("Cancelar").onClick(this::cancelar).setWidth(260);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Crear Metodologia Buffet");
		
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
		new Label(form).setText("Empresa");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form);
		selectorEmpresa.setWidth(250);
		selectorEmpresa.bindItemsToProperty("empresas");
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
	}
	
	public void crear(){
		if(this.getModelObject().noTieneEmpresa()){
			this.showError("seleccione una empresa");
		} else {
			this.getModelObject().crearMetodologiaBuffet();
			Operador.crearMetodologia
				(this.getModelObject().getMetodologiaBuffet());
			this.showInfo("Metodologia Creada");
			this.close();
		}
	}
	
	public void cancelar(){
		this.close();
	}

}
