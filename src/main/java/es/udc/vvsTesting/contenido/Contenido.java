package es.udc.vvsTesting.contenido;

import java.util.List;
/**
 * @author jose y pablo
 *
 */
public interface Contenido {
	/**
	 * Metodo que obtiene el titulo del contenido.
	 * @return String titulo del contenido
	 */
	String obtenerTitulo();
	/**
	 * Metodo que obtiene la duracion del contenido.
	 * @return int duracion del contenido
	 */
	int obtenerDuracion();
	/**
	 * Metodo que obtiene la lista de reproduccion del contenido.
	 * @return List<Contenido> lista de reproduccion del contenido
	 * */
	List<Contenido> obtenerListaReproduccion();
	/**
	 * Metodo que obtiene la lista de contenidos que coinciden con la busqueda
	 * por subcadena.
	 * @param subCadena
	 *            parametro de busqueda
	 * @return List<Contenido> lista de busquedas
	 * */
	List<Contenido> buscar(String subCadena);
	/**
	 * Metodo que agrega un contenido en orden despues de su predecesor si es
	 * null inserta al principio.
	 * @param content
	 *            contenido a agregar
	 * @param predecesor
	 *            predecesor despues del cual se debe insertar
	 * */
	void agregar(Contenido content, Contenido predecesor);
	/**
	 * Metodo que elimina la primera aparicion de un contenido.
	 * @param content
	 *            contenido a eliminar
	 * */
	void eliminar(Contenido content);
}
