package ventanas;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import viewModels.CargarCuenta;

public class CargarCuentaWindow extends Dialog<CargarCuenta> {

	public CargarCuentaWindow(WindowOwner parent) {
		super(parent, new CargarCuenta());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Cargar Archivo de Empresas");
		FileSelector fs = new FileSelector(mainPanel);
		fs.setCaption("Elegir Archivo de Empresas");
		fs.bindValueToProperty("nombreArchivo");
		fs.extensions("*.json");
		
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Importar Archivo").onClick(this::cargarCuenta).setWidth(520);
	}	
	
	protected void cargarCuenta() {
		// deberia tomar la ruta del archivo de cuentas del textbox y cargar el
		// archivo

			try{
				this.getModelObject().cargarArchivoDeCuentas();
				this.showInfo("Empresas Subidas a Base de Datos");
				this.close();
			} catch (Exception excepcion) {
				excepcion.printStackTrace();
				this.showWarning(excepcion.getMessage());
				}
			}
	}

			

