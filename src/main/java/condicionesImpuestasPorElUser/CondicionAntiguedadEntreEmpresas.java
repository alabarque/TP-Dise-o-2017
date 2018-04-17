package condicionesImpuestasPorElUser;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import entidades.Condicion;
import entidades.Empresa;


@Entity
@Table(name = "condicion_ver_empresa_mas_antigua") 
public class CondicionAntiguedadEntreEmpresas extends Condicion{
	
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	Empresa empresaAComparar;

	public CondicionAntiguedadEntreEmpresas(Empresa empresa){
		empresaAComparar = empresa;
		this.nombre = "Empresa mas antigua";
	}
	
	//para hibernate
	CondicionAntiguedadEntreEmpresas(){}
	
	
	
	@Override
	public Boolean evaluarEmpresa(Integer anio, Empresa empresaAEvaluar) {
		Integer longevidadEmpresaAEvaluar;
		longevidadEmpresaAEvaluar = this.antiguedad(empresaAEvaluar);
		
		return longevidadEmpresaAEvaluar < this.antiguedad(empresaAComparar);
	}
	
	private Integer antiguedad(Empresa empresa) {
		Integer longevidad;
		longevidad = empresa.getPeriodos().stream().map(periodo -> periodo.getPeriodo()).min((i1, i2) -> Integer.compare(i1,i2)).get();
		return longevidad;
	}

	
	
	public Empresa getEmpresaAComparar() {
		return empresaAComparar;
	}

	public void setEmpresaAComparar(Empresa empresaAComparar) {
		this.empresaAComparar = empresaAComparar;
	}


}
