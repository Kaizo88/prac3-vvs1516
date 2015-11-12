package es.udc.vvsTesting.utils;

import es.udc.vvsTesting.contenido.Contenido;

@SuppressWarnings("serial")
public class InsufficientPermissionsException extends Exception {

	String token;
	String action;
	Contenido content;

	public InsufficientPermissionsException(String token, String action,
			Contenido content) {
		this.action = action;
		this.content = content;
		this.token = token;
		System.out
				.println("No se puede "
						+ action
						+ " el contenido, no se posee el token de administrador. Token usado: "
						+ token);
	}
}
