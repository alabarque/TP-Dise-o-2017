
package ventanas;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import viewModels.Crear;

public class CrearWindow extends SimpleWindow<Crear> {

	public CrearWindow(WindowOwner owner) {
			super(owner, new Crear());
		}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Crear indicadores").onClick(this::crearIndicadores).setWidth(200);
		new Button(actionsPanel).setCaption("Crear metodologias").onClick(this::crearMetodologias).setWidth(200);

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Crear");
		new Panel(mainPanel);
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
