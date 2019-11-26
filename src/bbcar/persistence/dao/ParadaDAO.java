package bbcar.persistence.dao;

import java.util.Date;

import bbcar.persistence.classes.Parada;

public interface ParadaDAO {

	public Parada createParadaOrigen(Integer idViaje, String ciudad, String calle, Integer numero, Integer CP,
			Date fecha);
	
	public Parada createParadaDestino(Integer idViaje, String ciudad, String calle, Integer numero, Integer CP,
			Date fecha);

	public void update(Parada p) throws DAOException;
	
	public Parada getParada(Integer idParada);
}