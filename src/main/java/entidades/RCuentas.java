package entidades;

import java.util.ArrayList;
import java.util.List;

public class RCuentas {
	
	private String empresa;
	private String anio;
	private List<Cuenta> cuentas;
	
	public RCuentas(String empresa, String anio, List<Cuenta> cuentas) {
		super();
		this.empresa = empresa;
		this.anio = anio;
		this.cuentas = cuentas;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	
	


	
	
	

}
