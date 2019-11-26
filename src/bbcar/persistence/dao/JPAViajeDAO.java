package bbcar.persistence.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import bbcar.persistence.classes.Coche;
import bbcar.persistence.classes.Direccion;
import bbcar.persistence.classes.EntityManagerHelper;
import bbcar.persistence.classes.EstadoReserva;
import bbcar.persistence.classes.Parada;
import bbcar.persistence.classes.Reserva;
import bbcar.persistence.classes.Usuario;
import bbcar.persistence.classes.Viaje;

public class JPAViajeDAO implements ViajeDAO {

	public Viaje createViaje(Integer plazas, Double precio, Integer coche) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Viaje viaje = new Viaje();
		viaje.setNumPlazas(plazas);
		viaje.setPrecio(precio);
		viaje.setNotasViaje(new ArrayList<String>());
		viaje.setReservas(new ArrayList<Reserva>());
		Coche car = em.find(Coche.class, coche);
		viaje.setCoche(car);
		car.getViajes().add(viaje);
		em.persist(viaje);
		em.getTransaction().commit();
		em.close();
		return viaje;
	}

	@Override
	public void update(Viaje v) throws DAOException {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.merge(v);
		em.getTransaction().commit();
		em.close();
	}

	public List<Viaje> buscarViajes(String ciudadOrigen, String ciudadDestino, Date fechaHora) {
		try {
			String queryString = "SELECT v FROM Viaje AS v JOIN Parada AS porig JOIN Parada AS pdest WHERE v.origen.id = porig.id AND v.destino.id = pdest.id  AND porig.fecha >=:today";
			//String queryString = "SELECT v FROM Viaje AS v JOIN Parada AS porig JOIN Parada AS pdest JOIN Reserva AS r WHERE v.origen.id = porig.id AND v.destino.id = pdest.id AND r.viaje.id = v.id AND porig.fecha >=:today AND r.estado = :estado";
			if (ciudadOrigen != null && !ciudadOrigen.trim().isEmpty()) {
				queryString += " AND porig.ciudad =  :ciudadOrigen ";
			}
			if (ciudadDestino != null && !ciudadDestino.trim().isEmpty()) {
				queryString += " AND pdest.ciudad =:ciudadDestino ";
			}
			if (fechaHora != null) {
				queryString += " AND porig.fecha >=:fechaHora ";
			}
			EntityManager em = EntityManagerHelper.getEntityManager();
			Query q = em.createQuery(queryString);
			//q.setParameter("estado", EstadoReserva.PENDIENTE);
			q.setParameter("today", new Date());
			if (ciudadOrigen != null && !ciudadOrigen.trim().isEmpty()) {
				q.setParameter("ciudadOrigen", ciudadOrigen);
			}
			if (ciudadDestino != null && !ciudadDestino.trim().isEmpty()) {
				q.setParameter("ciudadDestino", ciudadDestino);
			}
			if (fechaHora != null) {
				q.setParameter("fechaHora", fechaHora);
			}
			q.setHint(QueryHints.REFRESH, HintValues.TRUE);
			return q.getResultList();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw (re);
		}
	}
	
	public Viaje getViaje(Integer idViaje) throws NumberFormatException, DAOException {
		/*if (PoolDAO.getInstance().contains("V"+idViaje)) {
			return (Viaje) PoolDAO.getInstance().getObject("V"+idViaje);
		}*/
		Viaje v = new Viaje();
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNativeQuery(
				"SELECT v.* FROM bbcar.viaje v WHERE v.ID = '" + idViaje + "';");
		if (!query.getResultList().isEmpty()) {
			@SuppressWarnings("unchecked")
			List<Object[]> vs = query.getResultList();
			for (Object[] viaje : vs) {
				v.setId(Integer.parseInt(viaje[0].toString()));
				v.setNumPlazas(Integer.parseInt(viaje[1].toString()));
				v.setPrecio(Double.parseDouble(viaje[2].toString()));
				if (viaje[3] != null) {
					v.setCoche(DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getCocheDAO()
							.getCoche(Integer.parseInt(viaje[3].toString())));
				}
				if (viaje[4] != null) {
					v.setDestino(DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getParadaDAO()
							.getParada(Integer.parseInt(viaje[4].toString())));
				}
				if (viaje[5] != null) {
					v.setOrigen(DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getParadaDAO()
							.getParada(Integer.parseInt(viaje[5].toString())));
				}
				/*v.setReservas(DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getReservaDAO()
							.getReservas(Integer.parseInt(viaje[0].toString())));*/
				
				
			}
			
			//PoolDAO.getInstance().addObject("V"+idViaje, v);

			return v;
		} else
			return null;
	}
}
