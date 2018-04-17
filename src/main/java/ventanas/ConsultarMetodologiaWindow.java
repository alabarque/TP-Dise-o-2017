package ventanas;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import viewModels.ConsultarMetodologia;

public class ConsultarMetodologiaWindow extends SimpleWindow<ConsultarMetodologia> {

	public ConsultarMetodologiaWindow(WindowOwner parent) {
		super(parent, new ConsultarMetodologia());
	}


	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Evaluar metodologia para una empresa").onClick(this::evaluarMetodologiaPara1Empresa).setAsDefault().setWidth(400);
		new Button(actionsPanel).setCaption("Evaluar meotodologia para N empresas").onClick(this::evaluarMetodologiaParaNEmpresas).setAsDefault().setWidth(400);
		new Button(actionsPanel).setCaption("Volver").onClick(this::close).setWidth(100);
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultar metodologias");
		new Panel(mainPanel);
		
	}
	
	protected void evaluarMetodologiaPara1Empresa(){
		SimpleWindow<?> window = new EvaluarMetodologiaPara1EmpresaWindow(this);
			window.open();
	}
	
	protected void evaluarMetodologiaParaNEmpresas(){
		SimpleWindow<?> window = new EvaluarMetodologiaParaNEmpresasWindow(this);
			window.open();
	}	

}
