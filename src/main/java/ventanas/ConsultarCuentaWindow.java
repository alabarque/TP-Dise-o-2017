package ventanas;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import entidades.Cuenta;
import entidades.Empresa;
import entidades.Operador;
import entidades.Periodo;
import viewModels.ConsultarCuenta;

public class ConsultarCuentaWindow extends SimpleWindow<ConsultarCuenta>{
		
	public ConsultarCuentaWindow(WindowOwner parent) {
		super(parent, new ConsultarCuenta());
		
	}

	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultar Cuenta");
		
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());
		
		new Label(form).setText("Empresa");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form);
		selectorEmpresa.setWidth(250);
		selectorEmpresa.bindItemsToProperty("empresas");
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		
		new Label(form).setText("Anio");
		Selector<Periodo> selectorPeriodos = new Selector<Periodo>(form);
		selectorPeriodos.setWidth(250);
		selectorPeriodos.bindItemsToProperty("empresaSeleccionada.periodos");
		selectorPeriodos.bindValueToProperty("periodoSeleccionado");
		
		/*new Label(form).setText("Semestre");
		Selector<Periodo> selectorSemestre = new Selector<Periodo>(form);
		selectorSemestre.bindItemsToProperty("semestre");
		selectorSemestre.bindValueToProperty("semestreSeleccionado");*/
		
		new Label(form).setText("Cuentas");
		
		Table<Cuenta> tableCuenta = new Table<Cuenta>(form, Cuenta.class);
		tableCuenta.setNumberVisibleRows(10);
		tableCuenta.bindItemsToProperty("cuentas");
		tableCuenta.bindValueToProperty("cuentas");
		
		Column<Cuenta> columnaID = new Column<Cuenta>(tableCuenta);
		columnaID.setFixedSize(120);
		columnaID.setTitle("Nombre");
		columnaID.bindContentsToProperty("nombre");
		
		Column<Cuenta> columnaNombre = new Column<Cuenta>(tableCuenta);
		columnaNombre.setFixedSize(120);
		columnaNombre.setTitle("Valor");
		columnaNombre.bindContentsToProperty("valor");		
		

		
	}


	@Override
	protected void addActions(Panel actionsPanel) {

		new Button(actionsPanel).setCaption("Consultar").onClick(this::consultarCuentas).setWidth(260);

	}
	
	protected void consultarCuentas() {
		try{
			Operador.consultarValorDeLasCuentas(this.getModelObject());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			this.showWarning(excepcion.getMessage());
			}
	}
}


