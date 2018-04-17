package entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import excepciones.BDException;


@Entity
@Table(name = "usuarios")

public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	
	private Integer usuarioId;
	
	public Usuario() {
		super();
	}
	
	
	public Usuario(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
	}

	// atributos
	@Column(name = "usuario", columnDefinition = "varchar(25)")
	private String usuario;
	@Column(name = "password", columnDefinition = "varchar(100)")
	private String password;
	
	
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public Usuario autorizacion() throws BDException {

		EntityManager em = PerThreadEntityManagers.getEntityManager();
		//Query query = em.createQuery("from Usuario where usuario = :usuario and password = :password");
		Query query2 = em.createQuery("from Usuario where usuario= :usuario");
		//query.setParameter("usuario", this.getUsuario());
		query2.setParameter("usuario", this.getUsuario());
		//query.setParameter("password", this.getPassword());
		//List<Usuario> usuario = query.getResultList();
		List<Usuario> usuario = query2.getResultList();
		em.clear();
		em.close();


		if (!usuario.isEmpty()) {
			PasswordHasher pwhasher = null;
			boolean retorno = pwhasher.checkPassword(this.getPassword(), usuario.get(0).getPassword());
			if(retorno){
				return usuario.get(0);
			}else{
			throw new BDException("Datos de Login Incorrectos");
		}
			
	}else {
		throw new BDException("Usuario no existe");
	}
		


	}
	
	public Boolean usuarioNoEstaRepetido(){
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		Query query = em.createQuery("from Usuario u where u.usuario = :usuario and u.password = :password");
		query.setParameter("usuario", this.getUsuario());
		query.setParameter("password", this.getPassword());
		List<Usuario> usuario = query.getResultList();
		em.clear();
		em.close();
		
		return usuario.isEmpty();
	}
	
	

}
