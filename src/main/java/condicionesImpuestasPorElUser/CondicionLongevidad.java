package condicionesImpuestasPorElUser;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import entidades.Condicion;
import entidades.Empresa;


@Entity
@Table(name = "condicion_ver_edad_empresa")
public class CondicionLongevidad extends Condicion{

	@Column(name = "anio")
	Integer anio;
	
	
	public CondicionLongevidad(Integer anioAComparar){
		anio = anioAComparar;
		this.nombre = "Condicion antiguedad empresa";
	}
	
	public CondicionLongevidad(){}

	@Override
	public Boolean evaluarEmpresa(Integer anioAComparar, Empresa empresa) {
		return empresa.getPeriodos().stream().anyMatch(periodo -> periodo.getPeriodo() < anio);
	}

	
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
}
