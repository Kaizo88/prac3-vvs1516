package es.udc.vvsTesting.contenido;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.udc.vvsTesting.utils.InvalidSongsDurationException;
/**
 * Implementacion de cancion.
 * @author jose y pablo
 *
 */
public class Cancion implements Contenido {
	/**
	 * Titulo.
	 */
	private String titulo;
	/**
	 * Duracion.
	 */
	private int duracion;
	/**
	 * Constructor de Cancion.
	 * @param titulo de la cancion
	 * @param duracion de la cancion
	 * @throws InvalidSongsDurationException excepcion lanzada
	 */
	public Cancion(final String titulo, final int duracion) throws InvalidSongsDurationException {
		if (duracion > 0) {
		this.titulo = titulo;
		this.duracion = duracion;
		} else {
			throw new InvalidSongsDurationException(duracion);
		}
	}
	/**
	 * Metodo que obtiene el titulo del contenido.
	 * @return String titulo del contenido
	 */
	public String obtenerTitulo() {
		return titulo;
	}
	/**
	 * Metodo que obtiene la duracion del contenido.
	 * @return int duracion del contenido
	 */
	public int obtenerDuracion() {
		return duracion;
	}
	/**
	 * Metodo que obtiene la lista de reproduccion del contenido.
	 * @return List<Contenido> lista de reproduccion del contenido
	 * */
	public List<Contenido> obtenerListaReproduccion() {
		List<Contenido> lista = new ArrayList<Contenido>();
		lista.add(this);
		return lista;
	}
	/**
	 * Metodo que obtiene la lista de contenidos que coinciden con la busqueda
	 * por subcadena.
	 * @param subChain
	 *            parametro de busqueda
	 * @return List<Contenido> lista de busquedas
	 * */
	public List<Contenido> buscar(final String subChain) {
		List<Contenido> lista = new ArrayList<Contenido>();
		if (contieneCadena(titulo, subChain)) {
			lista.add(this);
		}
		return lista;
	}
	/**
	 * Metodo que agrega un contenido en orden despues de su predecesor si es
	 * null inserta al principio.
	 * @param content
	 *            contenido a agregar
	 * @param predecesor
	 *            predecesor despues del cual se debe insertar
	 * */
	public void agregar(final Contenido content, final Contenido predecesor) {
		throw new UnsupportedOperationException("Agregar no soportado por Cancion");
	}
	/**
	 * Metodo que elimina la primera aparicion de un contenido.
	 * @param content
	 *            contenido a eliminar
	 * */
	public void eliminar(final Contenido content) {
		throw new UnsupportedOperationException("Eliminar no soportado por Cancion");
	}
	/**
	 * Comprueba si una cadena contiene a otra.
	 * @param str1 cadena 1
	 * @param str2 cadena 2
	 * @return boolean si la contiene
	 */
	private boolean contieneCadena(final String str1, final String str2) {
		return str1.toLowerCase(new Locale("es", "ES")).contains(str2.toLowerCase(new Locale("es", "ES")));
	}
	/**
	 * Override del hashCode().
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duracion;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}
	/**
	 * Override del equals().
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Cancion other = (Cancion) obj;
		if (duracion != other.duracion) {
			return false;
		}
		if (titulo == null) {
			if (other.titulo != null) {
				return false;
			}
		} else if (!titulo.equals(other.titulo)) {
			return false;
		}
		return true;
	}
}
