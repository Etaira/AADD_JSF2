package bbcar.persistence.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import bbcar.persistence.classes.Direccion;
import bbcar.persistence.classes.EntityManagerHelper;
import bbcar.persistence.classes.Parada;
import bbcar.persistence.classes.Viaje;

public class JPAParadaDAO implements ParadaDAO {

	public Parada createParadaOrigen(Integer idViaje, String ciudad, String calle, Integer numero, Integer CP,
			Date fecha) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Parada parada = new Parada();
		parada.setCiudad(ciudad);
		parada.setFecha(fecha);
		Direccion dir = new Direccion();
		dir.setCalle(calle);
		dir.setCP(CP);
		dir.setNumero(numero);
		parada.setDireccion(dir);
		Viaje viaje = em.find(Viaje.class, idViaje);
		viaje.setOrigen(parada);
		em.persist(parada);
		em.getTransaction().commit();
		em.close();
		return parada;
	}
	
	public Parada createParadaDestino(Integer idViaje, String ciudad, String calle, Integer numero, Integer CP,
			Date fecha) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Parada parada = new Parada();
		parada.setCiudad(ciudad);
		parada.setFecha(fecha);
		Direccion dir = new Direccion();
		dir.setCalle(calle);
		dir.setCP(CP);
		dir.setNumero(numero);
		parada.setDireccion(dir);
		Viaje viaje = em.find(Viaje.class, idViaje);
		viaje.setDestino(parada);
		em.persist(parada);
		em.getTransaction().commit();
		em.close();
		return parada;
	}
	
	public void update(Parada p) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		em.close();
	}
	
	public Parada getParada(Integer idParada) {
		Parada p = new Parada();
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNativeQuery(
				"SELECT p.* FROM bbcar.parada p WHERE p.ID = '" + idParada + "';");
		if (!query.getResultList().isEmpty()) {
			@SuppressWarnings("unchecked")
			List<Object[]> ps = query.getResultList();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
			Date date = null;
			for (Object[] parada : ps) {
				try {
					date = formatter.parse(parada[2].toString());
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				p.setId(Integer.parseInt(parada[0].toString()));
				p.setCiudad(parada[1].toString());
				p.setFecha(date);
				Direccion dir = new Direccion();
				dir.setCalle(parada[4].toString());
				dir.setCP(Integer.parseInt(parada[3].toString()));
				dir.setNumero(Integer.parseInt(parada[5].toString()));
				p.setDireccion(dir);
			}

			return p;
		} else
			return null;
	}
}
