/**
 * @author diego.campelo
 * @author jorge.garcia
 */
package es.udc.vvsTesting.servidor;

import java.util.List;

import es.udc.vvsTesting.contenido.Contenido;
import es.udc.vvsTesting.utils.ContentNotFoundException;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

/**
 * @author diego.campelo
 * Interfaz de Servidor
 *
 */
public interface Servidor {
	/**
	 * Metodo que obtiene el nombre del servidor.
	 * @return String nombre del servidor
	 * */
	String obtenerNombre();

	/**
	 * Metodo que genera un nuevo token en el servidor.
	 * @return String nuevo token
	 * */
	String alta();

	/**
	 * Metodo que elimina un token en el servidor si el token no existe
	 * devolvera una excepcion.
	 * @param token
	 *            token a eliminar
	 * @throws UnexistingTokenException
	 *             si no existe el token a eliminar
	 * */
	void baja(String token) throws UnexistingTokenException;

	/**
	 * Metodo que agrega contenido a el servidor si el token no
	 * es el token de administrador devolvera una excepcion.
	 * @param content
	 *            contenido a agregar en el servidor
	 * @param token
	 *            token usado para agregar contenido
	 * @throws InsufficientPermissionsException
	 *             si el token pasado carece de permisos
	 */

	void agregar(Contenido content, String token)
			throws InsufficientPermissionsException;

	/**
	 * Metodo que elimina contenido del servidor si el token
	 * no tiene permisos lanza un excepcion, si el
	 * contenido no existe en el servidor lanza una
	 * excepcion.
	 * @param content
	 *            contenido a eliminar del servidor
	 * @param token
	 *            token usado para eliminar contenido
	 * @throws InsufficientPermissionsException
	 *             si el token pasado carece de permisos
	 * @throws ContentNotFoundException
	 *             si el contenido no existe
	 * */
	void eliminar(Contenido content, String token)
			throws InsufficientPermissionsException,
			ContentNotFoundException;

	/**
	 * Metodo que devuelve una lista de contenido cuyo contenido
	 * contenga la subChain pasada, si el token pasado
	 * para buscar no existe lanza una excepcion.
	 * @param subChain
	 *            String usado para buscar
	 * @param token
	 *            token usado para buscar
	 * @throws UnexistingTokenException
	 *             si el token no existe
	 * @return List<Contenido> con los contenidos de la busqueda.
	 * */
	List<Contenido> buscar(String subChain, String token)
			throws UnexistingTokenException;

}
