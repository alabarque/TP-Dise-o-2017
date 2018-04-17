package ventanas;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import entidades.Empresa;
import entidades.Metodologia;
import entidades.Operador;
import entidades.Resultado;
import excepciones.MetodologiaException;
import viewModels.EvaluarMetodologiaPara1Empresa;

public class EvaluarMetodologiaPara1EmpresaWindow extends SimpleWindow<EvaluarMetodologiaPara1Empresa> {

	public EvaluarMetodologiaPara1EmpresaWindow(WindowOwner parent) {
		super(parent, new EvaluarMetodologiaPara1Empresa());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Consultar").onClick(this::consultar).setWidth(260);
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Evaluar Metodologia para 1 empresa");
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
		new Label(form).setText("Empresa");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form);
		selectorEmpresa.setWidth(250);
		selectorEmpresa.bindItemsToProperty("empresas");
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		
		new Label(form).setText("Metodologia");
		Selector<Metodologia> selectorMetodologia = new Selector<Metodologia>(form);
		selectorMetodologia.setWidth(250);
		selectorMetodologia.bindItemsToProperty("metodologias");
		selectorMetodologia.bindValueToProperty("metodologiaSeleccionada");
		
		new Label(form).setText("Seleccionar anio");
		new NumericField(form).bindValueToProperty("anio");
		
		Table<Resultado> tablaResultados = new Table<Resultado>(form, Resultado.class);
		
		tablaResultados.setNumberVisibleRows(15);
		tablaResultados.bindItemsToProperty("resultadoMetodologia");
		
		Column<Resultado> columnaEmpresa = new Column<Resultado>(tablaResultados);
		columnaEmpresa.setFixedSize(120);
		columnaEmpresa.setTitle("Nombre");
		columnaEmpresa.bindContentsToProperty("nombreEmpresa");
		
		Column<Resultado> columnaCondicion = new Column<Resultado>(tablaResultados);
		columnaCondicion.setFixedSize(120);
		columnaCondicion.setTitle("Condicion");
		columnaCondicion.bindContentsToProperty("nombreCondicion");	
		
		Column<Resultado> columnaResultado = new Column<Resultado>(tablaResultados);
		columnaResultado.setFixedSize(120);
		columnaResultado.setTitle("Condicion");
		columnaResultado.bindContentsToProperty("resultado");	
	}


	public void consultar(){
		try{
			this.getModelObject().consultarMetodologia();
		}catch(MetodologiaException me){
			me.printStackTrace();
			this.showWarning(me.getMessage());
		}
	}
	
}

	