package viewModels;

import org.uqbar.commons.utils.Observable;

import entidades.Metodologia;

@Observable
public class TipoDeCondicion {
	private Metodologia metodologia;

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
		
	}

	public Metodologia getMetodologia() {
				return this.metodologia;
	}

}
