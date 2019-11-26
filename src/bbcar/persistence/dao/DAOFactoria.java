package bbcar.persistence.dao;

public abstract class DAOFactoria {

	public final static int MYSQL = 1;
	public final static int JPA = 2;

	public static DAOFactoria getDAOFactoria(int jpa2) throws DAOException {
		switch (jpa2) {
		/*case MYSQL: {
			try {
				return new MySQLDAOFactoria();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		}*/
		case JPA:
			try {
				return new JPADAOFactoria();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		}
		return null;
	}

	/* Constructor */
	protected DAOFactoria() {
	}

	/* Nuevas entidades DAO */
	public abstract ParadaDAO getParadaDAO();
	public abstract ViajeDAO getViajeDAO();
	public abstract ValoracionDAO getValoracionDAO();
	public abstract UsuarioDAO getUsuarioDAO();
	public abstract CocheDAO getCocheDAO();
	public abstract ReservaDAO getReservaDAO();

}
