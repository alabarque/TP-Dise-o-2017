package ventanas;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import viewModels.VentanaPrincipal;


public class VentanaPrincipalWindow extends SimpleWindow<VentanaPrincipal> {

	public VentanaPrincipalWindow(WindowOwner parent) {
		super(parent, new VentanaPrincipal() );
	
	}

	
	@Override
	public void createFormPanel(Panel mainPanel) {
		this.setTitle("Analizador de Inversiones");
		
	}
	
	@Override
	protected void addActions(Panel panelBotones) {
		panelBotones.setLayout(new ColumnLayout(2));
		new Button(panelBotones).setCaption("Cargar Cuentas")
			.onClick(this::CargarCuentas).setWidth(250);
		
		new Button(panelBotones).setCaption("Consultar Cuentas")
		.onClick(this::consultarCuentas).setWidth(250);
		
		new Button(panelBotones).setCaption("Crear Indicadores")
		.onClick(this::crearIndicadores).setWidth(250);
		
		new Button(panelBotones).setCaption("Consultar Indicadores")
		.onClick(this::consultarIndicadores).setWidth(250);
		
		new Button(panelBotones).setCaption("Crear Metodologias")
		.onClick(this::crearMetodologias).setWidth(250);
		
		new Button(panelBotones).setCaption("Consultar Metodologias")
		.onClick(this::consultarMetodologias).setWidth(250);
		
		
	}
	
	
	public void CargarCuentas(){
		SimpleWindow<?> window = new CargarCuentaWindow(this);
		window.open();
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
	protected void crearIndicadores() {
		SimpleWindow<?> window = new CrearIndicadorWindow(this);
		window.open();
	}

	protected void crearMetodologias() {
		SimpleWindow<?> window = new CrearMetodologiaWindow(this);
		window.open();
	}
}
