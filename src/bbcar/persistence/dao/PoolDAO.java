package bbcar.persistence.dao;

import java.util.HashMap;

/**
 * Implementación del Pool necesario para persistir relaciones bidireccionales.
 * 
 * @author Vicente Javier Vidal-Abarca González
 * @author Eva de los Santos Nicolás
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
	 * Método que permite aplicar el patrón singleton creando
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
	 * Método que añade un nuevo objeto al Pool.
	 * @param id posición en la añadir el objeto
	 * @param object objeto a añadir
	 */
	public void addObject(String id, Object object) {
		pool.put(id, object);
	}

	/**
	 * Método para recuperar un objeto del pool.
	 * @param id id del objeto a recuperar
	 * @return objeto recuperado
	 */
	public Object getObject(String id) {
		return pool.get(id);
	}

	/**
	 * Método que comprueba si exite un objeto con id "id" en el pool.
	 * @param id id del objeto a comprobar
	 * @return booleando que indica si existe dicho objeto en el pool.
	 */
	public boolean contains(String id) {
		return pool.containsKey(id);
	}
}
