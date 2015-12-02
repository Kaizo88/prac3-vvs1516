package es.udc.vvsTesting.utils;

import es.udc.vvsTesting.contenido.Contenido;

/**
 * Clase excepcion.
 * @author diego.campelo
 *
 */
@SuppressWarnings("serial")
public class InsufficientPermissionsException extends Exception {

	/**
	 * Token.
	 */
	private String token;
	/**
	 * Accion.
	 */
	private String action;
	/**
	 * Contenido.
	 */
	private Contenido content;
	/**
	 * Constructor de la excepcion.
	 * @param token token usado
	 * @param action accion a realizar
	 * @param content contenido
	 */
	public InsufficientPermissionsException(final String token,
			final String action,
			final Contenido content) {
		this.action = action;
		this.content = content;
		this.token = token;
		System.out
				.println("Imposible "
						+ this.action
						+ " , permiso insuficiente"
						+ "Token usado: "
						+ this.token);
	}
}
