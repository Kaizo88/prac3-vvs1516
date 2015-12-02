package es.udc.vvsTesting.utils;

import es.udc.vvsTesting.contenido.Contenido;
/**
 * Excepcion lanzada cuando no se encuentra contenido.
 * @author diego
 *
 */
@SuppressWarnings("serial")
public class ContentNotFoundException extends Exception {
	/**
	 * Contenido que se buscaba.
	 */
	private Contenido content;
	/**
	 * Constructor de la excepcion.
	 * @param content contenido que se buscaba
	 */
	public ContentNotFoundException(final Contenido content) {
		this.content = content;
		System.out.println("Content :" + this.content.obtenerTitulo()
				+ " not found.");
	}
}
