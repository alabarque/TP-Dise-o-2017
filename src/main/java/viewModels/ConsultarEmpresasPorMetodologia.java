package viewModels;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import entidades.Metodologia;

@Observable
public class ConsultarEmpresasPorMetodologia {
	
	private List<Metodologia> metodologias;
	
	private Metodologia metodologiaSeleccionada;

	public Metodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}

	public void setMetodologiaSeleccionada(Metodologia metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
	}

	public List<Metodologia> getMetodologias() {
		return metodologias;
	}

	public void setMetodologias(List<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}
}
