package bbcar.persistence.dao;

import bbcar.persistence.classes.Coche;

public interface CocheDAO {

	public Coche createCoche(String matricula, String modelo, int anyo, int confort, Integer propietario)
			throws DAOException;

	public Coche findCocheByMatricula(String matricula) throws DAOException;

	public void update(Coche c) throws DAOException;

	Coche getCoche(Integer idCoche) throws DAOException;
}
