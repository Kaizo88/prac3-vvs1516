package es.udc.vvsTesting.utils;

import es.udc.vvsTesting.content.Content;

@SuppressWarnings("serial")
public class InsufficientPermissionsException extends Exception {

	String token;
	String action;
	Content content;

	public InsufficientPermissionsException(String token, String action, Content content) {
		this.action = action;
		this.content = content;
		this.token = token;
		System.out.println("No se puede "+action+" el contenido, no se posee el token de administrador. Token usado: " + token);
	}
}
