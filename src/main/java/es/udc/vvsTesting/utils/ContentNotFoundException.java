package es.udc.vvsTesting.utils;

import es.udc.vvsTesting.contenido.Contenido;

@SuppressWarnings("serial")
public class ContentNotFoundException extends Exception {
	Contenido content;

	public ContentNotFoundException(Contenido content) {
		this.content = content;
		System.out.println("Content :" + content.obtenerTitulo()
				+ " not found.");
	}
}
