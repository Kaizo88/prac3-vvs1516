package es.udc.vvsTesting.utils;

/**
 *
 * @author jose.pallas
 */
@SuppressWarnings("serial")
public class UnexistingTokenException extends Exception {

	/**
	 * token que se intentó usar.
	 */
	private String token;

	/**
	 * Constructor de la excepcion.
	 * @param token que se intento usar
	 */
	public UnexistingTokenException(final String token) {
		this.token = token;
		System.out.println("Token no existente: " + this.token);
	}
}
