package bbcar.persistence.dao;

import javax.persistence.EntityManager;

import bbcar.persistence.classes.EntityManagerHelper;
import bbcar.persistence.classes.Reserva;
import bbcar.persistence.classes.Usuario;
import bbcar.persistence.classes.Valoracion;

public class JPAValoracionDAO implements ValoracionDAO {

	@Override
	public Valoracion createValoracion(String comentario, Integer puntuacion, Integer reserva, Integer usuarioEmisor, Integer usuarioReceptor) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Valoracion valoracion = new Valoracion();
		valoracion.setComentario(comentario);
		valoracion.setPuntuacion(puntuacion);
		Reserva res = em.find(Reserva.class, reserva);
		Usuario ureceptor = em.find(Usuario.class, usuarioReceptor);
		Usuario uemisor = em.find(Usuario.class, usuarioEmisor);
		valoracion.setReserva(res);
		valoracion.setUsuarioEmisor(uemisor);
		valoracion.setUsuarioReceptor(ureceptor);
		res.getValoraciones().add(valoracion);
		uemisor.getValoracionesEmitidas().add(valoracion);
		ureceptor.getValoracionesRecibidas().add(valoracion);
		em.persist(valoracion);
		em.getTransaction().commit();
		em.close();
		return valoracion;
	}

	@Override
	public void update(Valoracion v) throws DAOException {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.merge(v);
		em.getTransaction().commit();
		em.close();
		
	}

}
