package bbcar.persistence.dao;

import java.util.Date;
import java.util.List;

import bbcar.persistence.classes.Viaje;

public interface ViajeDAO {

	public Viaje createViaje(Integer plazas, Double precio, Integer coche);

	public void update(Viaje v) throws DAOException;
	
	public List<Viaje> buscarViajes(String ciudadOrigen, String ciudadDestino, Date fechaHora);
	
	public Viaje getViaje(Integer idViaje) throws NumberFormatException, DAOException;
}
