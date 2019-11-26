package bbcar.persistence.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import bbcar.persistence.classes.Coche;
import bbcar.persistence.classes.EntityManagerHelper;
import bbcar.persistence.classes.Usuario;
import bbcar.persistence.classes.Viaje;

public class JPACocheDAO implements CocheDAO {

	@Override
	public Coche createCoche(String matricula, String modelo, int anyo, int confort, Integer propietario)
			throws DAOException {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Coche coche = new Coche();
		coche.setAnyo(anyo);
		coche.setConfort(confort);
		coche.setMatricula(matricula);
		coche.setModelo(modelo);
		coche.setViajes(new ArrayList<Viaje>());
		Usuario user = (Usuario) em.find(Usuario.class, propietario);
		coche.setPropietario(user);
		user.setCoche(coche);
		em.persist(coche);
		em.getTransaction().commit();
		em.close();
		return coche;
	}

	@Override
	public Coche findCocheByMatricula(String matricula) throws DAOException {
			Coche c = new Coche();
			EntityManager em = EntityManagerHelper.getEntityManager();
			Query query = em.createNativeQuery(
					"SELECT c.* FROM bbcar.coche c WHERE c.MATRICULA = '" + matricula + "';");
			if (!query.getResultList().isEmpty()) {
				@SuppressWarnings("unchecked")
				List<Object[]> cs = query.getResultList();
				for (Object[] coche : cs) {
					/*if (PoolDAO.getInstance().contains("C"+Integer.parseInt(coche[0].toString()))){
						return (Coche) PoolDAO.getInstance().getObject("C"+Integer.parseInt(coche[0].toString()));
					}*/
					c.setAnyo(Integer.parseInt(coche[1].toString()));
					c.setConfort(Integer.parseInt(coche[2].toString()));
					c.setId(Integer.parseInt(coche[0].toString()));
					c.setMatricula(coche[3].toString());
					c.setModelo(coche[4].toString());					
					Usuario user = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getUsuarioDAO().findUsuarioByUsuario(Integer.parseInt(coche[5].toString()));
					c.setPropietario(user);
				}
				
				//PoolDAO.getInstance().addObject("C"+c.getId(), c);
				return c;
			} else
				return null;
		}
	
	@Override
	public Coche getCoche(Integer idCoche) throws DAOException {
		/*if (PoolDAO.getInstance().contains("C"+idCoche)){
			return (Coche) PoolDAO.getInstance().getObject("C"+idCoche);
		}*/
			Coche c = new Coche();
			EntityManager em = EntityManagerHelper.getEntityManager();
			Query query = em.createNativeQuery(
					"SELECT c.* FROM bbcar.coche c WHERE c.ID = '" + idCoche + "';");
			if (!query.getResultList().isEmpty()) {
				@SuppressWarnings("unchecked")
				List<Object[]> cs = query.getResultList();
				for (Object[] coche : cs) {
					c.setAnyo(Integer.parseInt(coche[1].toString()));
					c.setConfort(Integer.parseInt(coche[2].toString()));
					c.setId(Integer.parseInt(coche[0].toString()));
					c.setMatricula(coche[3].toString());
					c.setModelo(coche[4].toString());
					Usuario user = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getUsuarioDAO().findUsuarioByUsuario(Integer.parseInt(coche[5].toString()));
					c.setPropietario(user);
				}
				
				//PoolDAO.getInstance().addObject("C"+idCoche, c);
				return c;
			} else
				return null;
		}

	@Override
	public void update(Coche c) throws DAOException {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();
		em.close();
	}

}
