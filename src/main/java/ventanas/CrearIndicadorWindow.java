package ventanas;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import basedatos.GrabarIndicador;
import parser.ParserIndicadores;
import repositorios.RepositorioIndicadores;
import viewModels.CrearIndicadores;

public class CrearIndicadorWindow extends SimpleWindow<CrearIndicadores> {

	public CrearIndicadorWindow(WindowOwner parent) {
		super(parent, new CrearIndicadores());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Crear Indicador").onClick(this::CrearCustom).setWidth(250);

		new Button(actionsPanel).setCaption("Cancelar").onClick(this::close).setWidth(250);

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Nuevo Indicador");
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));

		new Label(form).setText("Nombre del Indicador");
		new TextBox(form).setWidth(300).bindValueToProperty("nombre");
		new Label(form).setText("F�rmula");

		new TextBox(form).setWidth(500).bindValueToProperty("formula");
		;
	}

	protected void CrearCustom() {

		try {
			ParserIndicadores.analizar(this.getModelObject().getFormula());
			GrabarIndicador.GrabarIndicador(this.getModelObject().crearIndicador());
			this.showInfo("Indicador creado");
		} catch (Exception excepcion) {
			this.showWarning(excepcion.getMessage());
		}

	}

	protected void Cancelar() {

	}
}
