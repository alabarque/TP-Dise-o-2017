package ventanas;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import repositorios.RepositorioIndicadores;
import repositorios.RepositorioMetodologias;
import viewModels.Consultar;

public class ConsultarWindow extends SimpleWindow<Consultar>{

	public ConsultarWindow(WindowOwner parent) {
		super(parent, new Consultar());
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Consultar cuentas").onClick(this::consultarCuentas).setAsDefault().setWidth(200);
		new Button(actionsPanel).setCaption("Consultar indicadores").onClick(this::consultarIndicadores).setAsDefault().setWidth(200);
		new Button(actionsPanel).setCaption("Consultar metodologias").onClick(this::consultarMetodologias).setAsDefault().setWidth(200);
		new Button(actionsPanel).setCaption("Volver").onClick(this::close).setWidth(100);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultar");
		new Panel(mainPanel);
	}
	
	protected void consultarCuentas(){
		SimpleWindow<?> window = new ConsultarCuentaWindow(this);
		window.open();
	}
	
	protected void consultarIndicadores(){
		SimpleWindow<?> window = new ConsultarIndicadorWindow(this);
			window.open();
	}	
	
	protected void consultarMetodologias(){
		SimpleWindow<?> window = new ConsultarMetodologiaWindow(this);
		window.open();
		
	}

}
