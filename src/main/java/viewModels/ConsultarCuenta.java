package viewModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import entidades.Cuenta;
import entidades.Empresa;
import entidades.Periodo;
import excepciones.BDException;
import repositorios.RepositorioEmpresas;

@Observable
public class ConsultarCuenta {
	
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private Periodo periodoSeleccionado;
	private List<Cuenta> cuentas;
    EntityManager em = PerThreadEntityManagers.getEntityManager();

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentasSeleccionadas) {
		this.cuentas = cuentasSeleccionadas;
	}

	public Periodo getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(Periodo periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public List<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	//constructor
	public ConsultarCuenta() {
	    try {
			this.empresas = obtenerEmpresas();

	    } catch (Exception e) {
	    	e.printStackTrace();
	    } 
	}

	private List<Empresa> obtenerEmpresas() {
		return em.createQuery("from Empresa").getResultList();
	}

	
	//metodo llamado por el operador
	public void consultarCuentas() throws BDException {
	    try {
		this.cuentas = obtenerCuentas();
	    } catch (Exception e) {
	    	throw new BDException("Error al Consultar Cuentas");
	    } 
	}

	private List<Cuenta> obtenerCuentas() {
		return periodoSeleccionado.getCuentas();
	}

}
