package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;

import bbcar.persistence.classes.Coche;
import bbcar.persistence.classes.EntityManagerHelper;
import bbcar.persistence.classes.EstadoReserva;
import bbcar.persistence.classes.Reserva;
import bbcar.persistence.classes.Usuario;
import bbcar.persistence.classes.Valoracion;
import bbcar.persistence.classes.Viaje;
import controlador.BlaBlaCar;

public class TestJPA {

	@Test
	public void testRegistroUsuarioCoche() {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fecha = null;
		try {
			fecha = formatoDelTexto.parse("29/02/1964");
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		Integer user = BlaBlaCar.getInstance().registrarUsuario("Barrendeitor", "1234", "Basurero", "basurero.top@gmail.com", "Pepe",
				"Garcia", fecha).getId();
		Integer car = BlaBlaCar.getInstance().registrarCocheUsuario("9618BTR", "Dacia", 1990, 0, user).getId();

		EntityManager em = EntityManagerHelper.getEntityManager();
		Usuario usuarioJPA = em.find(Usuario.class, user);
		Coche cocheJPA = em.find(Coche.class, car);
		assertNotNull(usuarioJPA);
		assertNotNull(cocheJPA);
		assertEquals(usuarioJPA.getApellidos(), "Garcia");
		assertEquals(usuarioJPA.getClave(), "1234");
		assertEquals(usuarioJPA.getEmail(), "basurero.top@gmail.com");
		assertEquals(usuarioJPA.getFechaNacimiento(), fecha);
		assertEquals(usuarioJPA.getNombre(), "Pepe");
		assertEquals(usuarioJPA.getProfesion(), "Basurero");
		assertEquals(cocheJPA.getAnyo(), 1990);
		assertEquals(cocheJPA.getConfort(), 0);
		assertEquals(cocheJPA.getMatricula(), "9618BTR");
		assertEquals(cocheJPA.getModelo(), "Dacia");
		assertEquals(cocheJPA.getId(), usuarioJPA.getCoche().getId());
		assertEquals(cocheJPA.getPropietario().getId(), usuarioJPA.getId());
	}

	@Test
	public void testRegistroReserva() {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fecha = null;
		try {
			fecha = formatoDelTexto.parse("14/01/1997");
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		Integer user = BlaBlaCar.getInstance().registrarUsuario("DeViaje", "1235", "Funcionaria", "lafunci@gmail.com", "Laura",
				"Lopez", fecha).getId();
		java.util.Date fechaViaje = null;
		try {
			fechaViaje = formatoDelTexto.parse("05/06/2020");
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		Integer user2 = BlaBlaCar.getInstance().registrarUsuario("Te_Llevo", "1344", "Profesor", "prof@gmail.com", "Lucas", "Saura",
				fecha).getId();
		Integer car = BlaBlaCar.getInstance().registrarCocheUsuario("9009CTD", "Mercedes", 2004, 8, user2).getId();
		Integer idViaje = BlaBlaCar.getInstance().registrarViaje(3, 125.0, car).getId();
		BlaBlaCar.getInstance().registrarParadaOrigen(idViaje, "Cartagena", "C/Alameda", 6, 30204, fechaViaje);
		BlaBlaCar.getInstance().registrarParadaDestino(idViaje, "Murcia", "C/Gracia",13, 30002, fechaViaje);
		Integer reserva = BlaBlaCar.getInstance().reservarViaje( idViaje, user, "Maleta grande").getId();

		EntityManager em = EntityManagerHelper.getEntityManager();
		Reserva reservaJPA = em.find(Reserva.class, reserva);
		Viaje viajeJPA = em.find(Viaje.class, idViaje);
		Usuario userJPA = em.find(Usuario.class, user);
		Coche cocheJPA = em.find(Coche.class, car);
		assertNotNull(reservaJPA);
		assertNotNull(reservaJPA.getUsuario());
		assertNotNull(reservaJPA.getViaje());
		assertEquals(reservaJPA.getUsuario().getId(), user);
		assertEquals(reservaJPA.getViaje().getId(), idViaje);
		assertEquals(reservaJPA.getComentario(), "Maleta grande");
		assertEquals(reservaJPA.getEstado(), EstadoReserva.PENDIENTE);
		assertTrue(cocheJPA.getViajes().contains(viajeJPA));
		assertTrue(viajeJPA.getReservas().contains(reservaJPA));
		assertTrue(userJPA.getReservas().contains(reservaJPA));
	}

	@Test
	public void testRegistroValoracion() {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fechaUser1 = null;
		try {
			fechaUser1 = formatoDelTexto.parse("10/02/1995");
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		Integer user1 = BlaBlaCar.getInstance().registrarUsuario("Rosa33", "3333", "Ama de casa", "rosa_33@gmail.com", "Rosa",
				"Rubio", fechaUser1).getId();
		java.util.Date fechaViaje = null;
		try {
			fechaViaje = formatoDelTexto.parse("01/01/2020");
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		java.util.Date fechaUser2 = null;
		try {
			fechaUser2 = formatoDelTexto.parse("15/10/1988");
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		Integer user2 = BlaBlaCar.getInstance().registrarUsuario("SrcOche", "4321", "Enfermero", "sr56@gmail.com", "Jorge",
				"Saura", fechaUser2).getId();
		Integer car = BlaBlaCar.getInstance().registrarCocheUsuario("9338CCR", "Ford", 1999, 5, user2).getId();
		Integer idViaje = BlaBlaCar.getInstance().registrarViaje(2, 250.0, car).getId();
		BlaBlaCar.getInstance().registrarParadaOrigen(idViaje, "Murcia", "C/Mayorazgo", 25, 30005, fechaViaje);
		BlaBlaCar.getInstance().registrarParadaDestino(idViaje, "Manzanares", "C/Olimpia", 8, 13200, fechaViaje);
		Integer reserva = BlaBlaCar.getInstance().reservarViaje(idViaje, user1,"Llevo un perrito pequeño").getId();
		Integer valoracion = BlaBlaCar.getInstance().registrarValoracion("El coche me sorprendio, buen viaje", 10, reserva, user1,
				user2).getId();

		EntityManager em = EntityManagerHelper.getEntityManager();
		Valoracion valoracionJPA = em.find(Valoracion.class, valoracion);
		Usuario userJPAEmisor = em.find(Usuario.class, user1);
		Usuario userJPAReceptor = em.find(Usuario.class, user2);
		Reserva reservaJPA = em.find(Reserva.class, reserva);
		assertNotNull(valoracionJPA);
		assertEquals(valoracionJPA.getReserva().getId(), reserva);
		assertEquals(valoracionJPA.getUsuarioEmisor().getId(), user1);
		assertEquals(valoracionJPA.getUsuarioReceptor().getId(), user2);
		assertEquals(valoracionJPA.getPuntuacion(), 10);
		assertEquals(valoracionJPA.getComentario(), "El coche me sorprendio, buen viaje");
		assertTrue(userJPAEmisor.getValoracionesEmitidas().contains(valoracionJPA));
		assertTrue(userJPAReceptor.getValoracionesRecibidas().contains(valoracionJPA));
		assertFalse(userJPAReceptor.getValoracionesEmitidas().contains(valoracionJPA));
		assertFalse(userJPAEmisor.getValoracionesRecibidas().contains(valoracionJPA));
		assertTrue(reservaJPA.getValoraciones().contains(valoracionJPA));
	}
}
