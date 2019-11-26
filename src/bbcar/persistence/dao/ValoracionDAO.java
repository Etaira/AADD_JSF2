package bbcar.persistence.dao;

import bbcar.persistence.classes.Valoracion;

public interface ValoracionDAO {

	public Valoracion createValoracion(String comentario, Integer puntuacion, Integer reserva, Integer usuarioEmisor, Integer usuarioReceptor);

	public void update(Valoracion v) throws DAOException;;
}
