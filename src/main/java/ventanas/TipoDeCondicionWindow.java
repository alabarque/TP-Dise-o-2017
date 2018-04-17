package ventanas;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import condicionesImpuestasPorElUser.CondicionLongevidad;
import condicionesImpuestasPorElUser.MetodologiaBuffet;
import entidades.Metodologia;
import viewModels.TipoDeCondicion;

public class TipoDeCondicionWindow extends SimpleWindow<TipoDeCondicion> {

	public TipoDeCondicionWindow(WindowOwner parent, Metodologia metodologia) {
		super(parent, new TipoDeCondicion());
		this.getModelObject().setMetodologia(metodologia);
	}


	@Override
	protected void addActions(Panel actionsPanel) {
		actionsPanel.setLayout(new ColumnLayout(2));
		new Button(actionsPanel).setCaption("Longevidad").onClick(this::longevidad).setWidth(260);
		new Button(actionsPanel).setCaption("Antiguedad entre empresas").onClick(this::antiguedad).setWidth(260);
		new Button(actionsPanel).setCaption("Comparar Indicador").onClick(this::compInd).setWidth(260);
		new Button(actionsPanel).setCaption("Crecimiento de Indicador").onClick(this::crecDeInd).setWidth(260);
		new Button(actionsPanel).setCaption("Evaluar Periodo").onClick(this::evaluarPeriodo).setWidth(260);
		new Button(actionsPanel).setCaption("Cerrar ventana").onClick(this::cancelar).setWidth(260);
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Elegir tipo de condicion");
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());	
	}
	
	protected void longevidad(){
		
		SimpleWindow<?> window = new CondicionDeLongevidadWindow(this, this.getModelObject().getMetodologia());
		window.open();
		this.close();
	}

	protected void antiguedad(){
		SimpleWindow<?> window = new CondicionDeAntiguedadWindow(this, this.getModelObject().getMetodologia());
		window.open();
		this.close();
	}

	protected void compInd(){
		SimpleWindow<?> window = new CondicionDeCompararIndicadorWindow(this, this.getModelObject().getMetodologia());
		window.open();
		this.close();
	}

	protected void crecDeInd(){
		SimpleWindow<?> window = new CondicionDeCrecimientoIndicadorWindow(this, this.getModelObject().getMetodologia());
		window.open();
		this.close();
	}
	
	protected void evaluarPeriodo(){
		SimpleWindow<?> window = new CondicionDeEvaluarPeriodoNAniosWindow(this, this.getModelObject().getMetodologia());
		window.open();
		this.close();
	}
	
	protected void cancelar(){
		this.close();
		SimpleWindow<?> window = new PersonalizarMetodologiaWindow(this, this.getModelObject().getMetodologia());
		window.open();
	}
}
