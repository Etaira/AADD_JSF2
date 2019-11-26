package controlador;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import bbcar.persistence.bean.*;
import bbcar.persistence.classes.Coche;
import bbcar.persistence.classes.EstadoReserva;
import bbcar.persistence.classes.Parada;
import bbcar.persistence.classes.Reserva;
import bbcar.persistence.classes.Usuario;
import bbcar.persistence.classes.Valoracion;
import bbcar.persistence.classes.Viaje;
import bbcar.persistence.dao.*;

public class BlaBlaCar {

	private static BlaBlaCar unicaInstancia;

	public static BlaBlaCar getInstance() {
		if (unicaInstancia == null)
			unicaInstancia = new BlaBlaCar();
		return unicaInstancia;
	}

	public Viaje registrarViaje(Integer plazas, Double precio, Integer coche) {
		try {
			ViajeDAO viajeDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getViajeDAO();
			Viaje viaje = viajeDAO.createViaje(plazas, precio, coche);
			return viaje;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Parada registrarParadaOrigen(Integer idViaje, String ciudad, String calle, Integer numero, Integer CP,
			Date fecha) {
		try {
			ParadaDAO paradaDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getParadaDAO();
			Parada parada = paradaDAO.createParadaOrigen(idViaje, ciudad, calle, numero, CP, fecha);
			return parada;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Parada registrarParadaDestino(Integer idViaje, String ciudad, String calle, Integer numero, Integer CP,
			Date fecha) {
		try {
			ParadaDAO paradaDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getParadaDAO();
			Parada parada = paradaDAO.createParadaDestino(idViaje, ciudad, calle, numero, CP, fecha);
			return parada;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario registrarUsuario(String usuario, String clave, String profesion, String mail, String nombre,
			String apellidos, Date fechaNacimiento) {
		Usuario user = null;
		try {
			UsuarioDAO usuarioDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getUsuarioDAO();
			user = usuarioDAO.createUsuario(usuario, clave, profesion, mail, nombre, apellidos, fechaNacimiento);

		} catch (DAOException e) {
			e.printStackTrace();
		}
		return user;
	}

	public Coche registrarCocheUsuario(String matricula, String modelo, int anyo, int confort, Integer propietario) {
		try {
			CocheDAO cocheDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getCocheDAO();
			Coche coche = cocheDAO.createCoche(matricula, modelo, anyo, confort, propietario);
			return coche;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Reserva reservarViaje(Integer viaje, Integer usuario, String comentario) {
		try {
			ReservaDAO reservaDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getReservaDAO();
			Reserva reserva = reservaDAO.createReserva(comentario, EstadoReserva.PENDIENTE, viaje, usuario);
			return reserva;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Valoracion registrarValoracion(String comentario, Integer puntuacion, Integer reserva, Integer usuarioEmisor,
			Integer usuarioReceptor) {
		try {
			ValoracionDAO valoracionDAO = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getValoracionDAO();
			Valoracion valoracion = valoracionDAO.createValoracion(comentario, puntuacion, reserva, usuarioEmisor,
					usuarioReceptor);
			return valoracion;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean login(String username, String password) throws Exception {
		Usuario u = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getUsuarioDAO().getUsuario(username, password);
		if(u != null) {
			if (!u.claveCorrecta(password)) {
				throw new Exception("Contraseña incorrecta!");
			}
			return true;
		}else {
			return false;
		}
	}

	public Usuario getUsuario(String username, String password) throws DAOException {
		return DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getUsuarioDAO().getUsuario(username, password);
	}
	
	public Coche findCocheByMatricula(String matricula) throws DAOException {
		return DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getCocheDAO().findCocheByMatricula(matricula);
	}
	
	public Coche getCoche(Integer idCoche) throws DAOException {
		return DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getCocheDAO().getCoche(idCoche);
	}
	
	public Coche updateCoche(Coche c, String matricula, String modelo, int anyo, int confort) {
		c.setMatricula(matricula);
		c.setModelo(modelo);
		c.setAnyo(anyo);
		c.setConfort(confort);
		try {
			DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getCocheDAO().update(c);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return c;
	}

	public List<Viaje> buscarViajes(String ciudadOrigen, String ciudadDestino, Date fechaHora) {
		try {
			return DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getViajeDAO().buscarViajes(ciudadOrigen, ciudadDestino,
					fechaHora);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Viaje getViaje(Integer idViaje) {
		try {
			return DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getViajeDAO().getViaje(idViaje);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
