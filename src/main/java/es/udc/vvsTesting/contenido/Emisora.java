package es.udc.vvsTesting.contenido;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * Implementacion de emisora.
 * @author jose y pablo
 *
 */
public class Emisora implements Contenido {
	/**
	 * Titulo de la emisora.
	 */
	private String titulo;
	/**
	 * Duracion de los contenidos de la emisora.
	 */
	private int duracion;
	/**
	 * Lista de contenidos de la emisora.
	 */
	private List<Contenido> listaReproduccion;
	/**
	 * Constructor.
	 * @param titulo de la emisora
	 */
	public Emisora(final String titulo) {
		this.titulo = titulo;
		this.duracion = 0;
		this.listaReproduccion = new ArrayList<Contenido>();
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
		return listaReproduccion;
	}
	/**
	 * Metodo que obtiene la lista de contenidos que coinciden con la busqueda
	 * por subcadena.
	 * @param subCadena
	 *            parametro de busqueda
	 * @return List<Contenido> lista de busquedas
	 * */
	public List<Contenido> buscar(final String subCadena) {
		List<Contenido> lista = new ArrayList<Contenido>();
		for (int i = 0; i < listaReproduccion.size(); i++) {
			if (contieneCadena(listaReproduccion.get(i).obtenerTitulo(),
					subCadena)) {
				lista.add(listaReproduccion.get(i));
			}
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
		if (predecesor == null) { // insertar por el principio
			if (listaReproduccion.isEmpty()) {
				listaReproduccion.add(content);
				this.duracion = this.duracion + content.obtenerDuracion();
			} else {
				listaReproduccion.add(0, content);
				this.duracion = this.duracion + content.obtenerDuracion();
			}
		} else { // Sino insertar despues de predecesor
			List<Contenido> listaAux = new ArrayList<Contenido>();
			int i = 0;
				while (i < listaReproduccion.size()) {
					listaAux.add(listaReproduccion.get(i));
					if (listaReproduccion.get(i)
							.equals(predecesor)) {
						listaAux.add(content);
						this.duracion = this.duracion + content.obtenerDuracion();
					}
					i++;
				}
				listaReproduccion = listaAux;
		}

		/*
		 * Si hay canciones repetidas agregamos después de todas las
		 * predecesoras
		 */
	}
	/**
	 * Metodo que elimina la primera aparicion de un contenido.
	 * @param content
	 *            contenido a eliminar
	 * */
	public void eliminar(final Contenido content) {
		if (listaReproduccion.isEmpty()) {
			return;
		} else {
			int contains = 0;
			for (int i = 0; i < listaReproduccion.size(); i++) {
				if (listaReproduccion.get(i).equals(content)) {
					contains++;
				}
			}
			while (contains != 0) {
				listaReproduccion.remove(content);
				this.duracion = this.duracion - content.obtenerDuracion();
				contains--;
			}
		}
	}
	/**
	 * Comprueba si contiene la cadena.
	 * @param str1 cadena 1
	 * @param str2 cadena 2
	 * @return boolean si la contiene.
	 */
	private boolean contieneCadena(final String str1, final String str2) {
		return str1.toLowerCase(new Locale("es", "ES")).contains(str2.toLowerCase(new Locale("es", "ES")));
	}
}
