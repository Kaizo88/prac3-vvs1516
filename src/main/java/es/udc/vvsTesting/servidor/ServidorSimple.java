package es.udc.vvsTesting.servidor;

/**
 * Clase implementador de Servidor Simple.
 * @author diego
 *
 */
public class ServidorSimple extends ServidorImp {
	/**
	 * Constructor para el servidor simple.
	 * @param nombre nombre servidor
	 * @param tokenAdmin token para el admin del servidor
	 */
	public ServidorSimple(final String nombre, final String tokenAdmin) {
		super(nombre, tokenAdmin);
	}
}
