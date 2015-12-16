package es.udc.vvsTesting.utils;

/**
 * Excepción de duracion invalida.
 * @author jose
 *
 */
public class InvalidSongsDurationException extends Exception {
	/**
	 * Duracion introducida que lanzo la excepcion.
	 */
	private int duration;
	/**
	 * Constructor de la excepcion.
	 * @param duration duracion introducida.
	 */
	public InvalidSongsDurationException(final int duration) {
		super("La duración debe ser postiva. La duracion "
				+ "pasada son " + Integer.toString(duration) + " segundos");
	}
}
