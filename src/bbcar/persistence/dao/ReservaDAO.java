package bbcar.persistence.dao;

import java.util.List;

import bbcar.persistence.classes.EstadoReserva;
import bbcar.persistence.classes.Reserva;

public interface ReservaDAO {

	public Reserva createReserva(String comentario, EstadoReserva estado, Integer viaje ,Integer usuario);

	public void update(Reserva r) throws DAOException;
	
	public Reserva getReserva(Integer idReserva) throws NumberFormatException, DAOException;
	
	public List<Reserva> getReservas(Integer idViaje) throws NumberFormatException, DAOException;
}
