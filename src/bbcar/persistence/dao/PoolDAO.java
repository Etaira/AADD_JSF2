package bbcar.persistence.dao;

import java.util.HashMap;

/**
 * Implementaci�n del Pool necesario para persistir relaciones bidireccionales.
 * 
 * @author Vicente Javier Vidal-Abarca Gonz�lez
 * @author Eva de los Santos Nicol�s
 *
 */
public class PoolDAO {
	
	/**
	 * 
	 * Atributos de la clase
	 * 
	 */
	private static PoolDAO unicaInstancia = null;
	private HashMap<String, Object> pool;

	/**
	 * 
	 * Constructor de la clase.
	 * 
	 */
	private PoolDAO() {
		pool = new HashMap<String, Object>();
	}

	/**
	 * M�todo que permite aplicar el patr�n singleton creando
	 * la primera vez que se utiliza la unica instancia de
	 * esta clase y devolviendola en las siguientes llamadas.
	 * 
	 * @return unicaInstancia
	 */
	public static PoolDAO getInstance() {
		if (unicaInstancia == null)
			unicaInstancia = new PoolDAO();
		return unicaInstancia;
	}

	/**
	 * M�todo que a�ade un nuevo objeto al Pool.
	 * @param id posici�n en la a�adir el objeto
	 * @param object objeto a a�adir
	 */
	public void addObject(String id, Object object) {
		pool.put(id, object);
	}

	/**
	 * M�todo para recuperar un objeto del pool.
	 * @param id id del objeto a recuperar
	 * @return objeto recuperado
	 */
	public Object getObject(String id) {
		return pool.get(id);
	}

	/**
	 * M�todo que comprueba si exite un objeto con id "id" en el pool.
	 * @param id id del objeto a comprobar
	 * @return booleando que indica si existe dicho objeto en el pool.
	 */
	public boolean contains(String id) {
		return pool.containsKey(id);
	}
}
