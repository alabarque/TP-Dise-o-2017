package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import entidades.Metodologia;

public class RepositorioMetodologias implements WithGlobalEntityManager{
	
	public static List<Metodologia> metodologias = new ArrayList<Metodologia>();
	public static RepositorioMetodologias instancia = new RepositorioMetodologias();

	public RepositorioMetodologias (List<Metodologia> metodologias) {
		super();
		RepositorioMetodologias.metodologias = metodologias;
	}
	
	public RepositorioMetodologias(){};
	
	
	/*
	 * TODO
	 */
	public void obtenerMetodologias(){
		metodologias = entityManager().createQuery("from Metodologia", Metodologia.class).getResultList();
	}
	
	public static void agregarMetodologias(List<Metodologia> listaMetodologias) {
		metodologias.addAll(listaMetodologias);
		
	}
	
	public static void agregarMetodologia(Metodologia metodologia) {
		metodologias.add(metodologia);
	}
	
	public static boolean tieneElementos() {
		return (metodologias.size() > 0);
	}
	
	public static List<Metodologia> getMetodologias(){
		return metodologias;
	}
	
	public static Metodologia obtenerMetodologia(String metodologia){
		return metodologias.stream().filter(unaMetodologia -> unaMetodologia.getNombre().equals(metodologia)).findFirst().get();
	}

}
