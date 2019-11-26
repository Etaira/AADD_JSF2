package bbcar.persistence.dao;
import java.util.Date;

import bbcar.persistence.classes.Usuario;
import bbcar.persistence.dao.DAOException;

public interface UsuarioDAO {

	public Usuario createUsuario(String usuario, String clave, String profesion, String mail, String nombre,
			String apellidos, Date fechaNacimiento) throws DAOException;
	
	public Usuario getUsuario(String username,String password) throws DAOException;

	public Usuario findUsuarioByUsuario(Integer propietario) throws DAOException;

	@SuppressWarnings("rawtypes")
	public java.util.Collection findAll() throws DAOException;

	public void update(Usuario c) throws DAOException;
}
