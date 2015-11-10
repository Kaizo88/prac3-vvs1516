package es.udc.vvsTesting.utils;

import es.udc.vvsTesting.content.Content;

@SuppressWarnings("serial")
public class ContentNotFoundException extends Exception {
	Content content;

	public ContentNotFoundException(Content content) {
		this.content = content;
		System.out.println("Content :" + content.obtenerTitulo()
				+ " not found.");
	}
}
