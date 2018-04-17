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
import entidades.Metodologia;
import excepciones.MetodologiaException;
import viewModels.EvaluarMetodologiaParaNEmpresas;

public class EvaluarMetodologiaParaNEmpresasWindow extends SimpleWindow<EvaluarMetodologiaParaNEmpresas> {

	public EvaluarMetodologiaParaNEmpresasWindow(WindowOwner parent) {
		super(parent, new EvaluarMetodologiaParaNEmpresas());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Consultar").onClick(this::consultar).setWidth(260);
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Evaluar Metodologia para todas las empresas");
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
		new Label(form).setText("Metodologia");
		Selector<Metodologia> selectorMetodologia = new Selector<Metodologia>(form);
		selectorMetodologia.setWidth(250);
		selectorMetodologia.bindItemsToProperty("metodologias");
		selectorMetodologia.bindValueToProperty("metodologiaSeleccionada");
		
		new Label(form).setText("Seleccionar anio");
		new NumericField(form).bindValueToProperty("anio");
	}
	
	
	public void consultar(){
		try{
			this.getModelObject().verificarNoNulos();
			SimpleWindow<?> window = new ListaEmpresasEvaluadasWindow(
					this, this.getModelObject().getMetodologiaSeleccionada(), Integer.parseInt(this.getModelObject().getAnio()));
			window.open();
		}catch(MetodologiaException me){
			me.printStackTrace();
			this.showWarning(me.getMessage());
		}
	}

}
