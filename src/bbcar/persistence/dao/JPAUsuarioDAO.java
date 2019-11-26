package bbcar.persistence.dao;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import bbcar.persistence.classes.EntityManagerHelper;
import bbcar.persistence.classes.Reserva;
import bbcar.persistence.classes.Usuario;
import bbcar.persistence.classes.Valoracion;
import controlador.BlaBlaCar;

public class JPAUsuarioDAO implements UsuarioDAO {

	@Override
	public Usuario createUsuario(String usuario, String clave, String profesion, String mail, String nombre,
			String apellidos, Date fechaNacimiento) throws DAOException {
		EntityManager em = EntityManagerHelper.getEntityManager();
		if (this.getUsuario(usuario, clave) == null) {
			em.getTransaction().begin();
			Usuario user = new Usuario();
			user.setApellidos(apellidos);
			user.setClave(clave);
			user.setEmail(mail);
			user.setFechaNacimiento(fechaNacimiento);
			user.setNombre(nombre);
			user.setProfesion(profesion);
			user.setUsuario(usuario);
			user.setValoracionesEmitidas(new ArrayList<Valoracion>());
			user.setValoracionesRecibidas(new ArrayList<Valoracion>());
			user.setReservas(new ArrayList<Reserva>());
			em.persist(user);
			em.getTransaction().commit();
			em.close();
			return user;
		} else
			return null;
	}

	@Override
	public Usuario findUsuarioByUsuario(Integer usuario) throws DAOException {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Usuario user = em.find(Usuario.class, usuario);
		em.getTransaction().commit();
		em.close();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Usuario> findAll() throws DAOException {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("SELECT * FROM usuario");
		return (Collection<Usuario>) query.getResultList();
	}

	@Override
	public void update(Usuario u) throws DAOException {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Usuario getUsuario(String username, String password) throws DAOException {
		Usuario user = new Usuario();
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNativeQuery(
				"SELECT u.* FROM bbcar.usuario u WHERE u.USUARIO = '" + username + "' AND u.CLAVE='" + password + "';");
		if (!query.getResultList().isEmpty()) {
			@SuppressWarnings("unchecked")
			List<Object[]> us = query.getResultList();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
			Date date = null;
			for (Object[] usuario : us) {
				/*if (PoolDAO.getInstance().contains("U"+Integer.parseInt(usuario[0].toString()))){
					return (Usuario) PoolDAO.getInstance().getObject("U"+Integer.parseInt(usuario[0].toString()));
				}*/
				try {

					date = formatter.parse(usuario[4].toString());
					System.out.println(date);
					System.out.println(formatter.format(date));

				} catch (ParseException e) {
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				user.setId(Integer.parseInt(usuario[0].toString()));
				user.setApellidos(usuario[1].toString());
				user.setClave(usuario[2].toString());
				user.setEmail(usuario[3].toString());
				user.setFechaNacimiento(date);
				user.setNombre(usuario[5].toString());
				user.setProfesion(usuario[6].toString());
				user.setUsuario(usuario[7].toString());
				if (usuario[8] != null) {
					user.setCoche(DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getCocheDAO()
							.getCoche(Integer.parseInt(usuario[8].toString())));
				}
			}

			//PoolDAO.getInstance().addObject("U"+user.getId(), user);
			return user;
		} else
			return null;
	}

}
