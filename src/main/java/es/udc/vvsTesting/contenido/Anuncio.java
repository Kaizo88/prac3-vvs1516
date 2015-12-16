package es.udc.vvsTesting.contenido;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementacion de anuncio.
 * @author jose y pablo
 *
 */
public class Anuncio implements Contenido {
	/**
	 * Titulo del anuncio.
	 */
	private String titulo;
	/**
	 * Duracion del anuncio.
	 */
	private int duracion;
	/**
	 * Duracion base.
	 */
	private static final int DURA = 5;

	/**
	 * Constructor.
	 */
	public Anuncio() {
		this.titulo = "PUBLICIDAD";
		this.duracion = DURA;
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
		if (subChain.equalsIgnoreCase(titulo)) {
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
		throw new UnsupportedOperationException("Agregar no soportado por Anuncio");
	}

	/**
	 * Metodo que elimina la primera aparicion de un contenido.
	 * @param content
	 *            contenido a eliminar
	 * */
	public void eliminar(final Contenido content) {
		throw new UnsupportedOperationException("Eliminar no soportado por Anuncio");	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duracion;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

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
		 return true;
	}

}
