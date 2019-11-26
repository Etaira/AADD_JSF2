package bbcar.persistence.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import bbcar.persistence.classes.Direccion;
import bbcar.persistence.classes.EntityManagerHelper;
import bbcar.persistence.classes.EstadoReserva;
import bbcar.persistence.classes.Parada;
import bbcar.persistence.classes.Reserva;
import bbcar.persistence.classes.Usuario;
import bbcar.persistence.classes.Valoracion;
import bbcar.persistence.classes.Viaje;

public class JPAReservaDAO implements ReservaDAO {

	@Override
	public Reserva createReserva(String comentario, EstadoReserva estado, Integer viaje ,Integer usuario) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Reserva reserva = new Reserva();
		reserva.setComentario(comentario);
		reserva.setEstado(estado);
		reserva.setValoraciones(new ArrayList<Valoracion>());
		Usuario user = (Usuario) em.find(Usuario.class, usuario);
		reserva.setUsuario(user);
		Viaje travel = (Viaje) em.find(Viaje.class, viaje);
		reserva.setViaje(travel);
		user.getReservas().add(reserva);
		travel.getReservas().add(reserva);
		em.persist(reserva);
		em.getTransaction().commit();
		em.close();
		return reserva;
	}

	@Override
	public void update(Reserva r) throws DAOException {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.merge(r);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Reserva getReserva(Integer idReserva) throws NumberFormatException, DAOException{
		/*if (PoolDAO.getInstance().contains("R"+idReserva)) {
			return (Reserva) PoolDAO.getInstance().getObject("R"+idReserva);
		}*/
		Reserva r = new Reserva();
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNativeQuery(
				"SELECT r.* FROM bbcar.reserva r WHERE r.ID = '" + idReserva + "';");
		if (!query.getResultList().isEmpty()) {
			@SuppressWarnings("unchecked")
			List<Object[]> rs = query.getResultList();
			for (Object[] reserva : rs) {
				r.setId(Integer.parseInt(reserva[0].toString()));
				r.setComentario(reserva[1].toString());
				EstadoReserva er = Enum.valueOf(EstadoReserva.class, reserva[2].toString());
				r.setEstado(er);
				if (reserva[3] != null) {
					r.setUsuario(DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getUsuarioDAO().findUsuarioByUsuario
							(Integer.parseInt(reserva[3].toString())));
				}
				if (reserva[4] != null) {
					r.setViaje(DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getViajeDAO()
							.getViaje(Integer.parseInt(reserva[4].toString())));
				}
			}
			//PoolDAO.getInstance().addObject("R"+r.getId(), r);

			return r;
		} else
			return null;
	}

	@Override
	public List<Reserva> getReservas(Integer idViaje) throws NumberFormatException, DAOException{
		List<Reserva> lr = new LinkedList<Reserva>();
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNativeQuery(
				"SELECT r.* FROM bbcar.reserva r WHERE r.VIAJE_ID = '" + idViaje + "';");
		if (!query.getResultList().isEmpty()) {
			@SuppressWarnings("unchecked")
			List<Object[]> rs = query.getResultList();
			for (Object[] reserva : rs) {
				lr.add(this.getReserva(Integer.valueOf(reserva[0].toString())));
			}
		}
		
		return lr;
	}
	
	

}
