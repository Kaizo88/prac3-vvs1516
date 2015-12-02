package es.udc.vvsTesting.servidor;

import java.util.List;

import es.udc.vvsTesting.contenido.Contenido;
import es.udc.vvsTesting.utils.UnexistingTokenException;

/**
 * Implementacion del servidor con respaldo.
 * @author diego
 *
 */
public class ServidorConRespaldo extends ServidorImp {

	/**
	 * Variable en la que se almacena el servidor de respaldo.
	 */
	private Servidor servidorRespaldo;

	/**
	 * Constructor para el servidor con respaldo.
	 * @param nombre nombre del servidor
	 * @param tokenAdmin token del admin
	 * @param serverRespaldo servidor de respaldo
	 */
	public ServidorConRespaldo(final String nombre, final String tokenAdmin,
			final Servidor serverRespaldo) {
		super(nombre, tokenAdmin);
		this.servidorRespaldo = serverRespaldo;
	}

	/**
	 * Metodo que devuelve una lista de contenido cuyo contenido
	 * contenga la subChain pasada, si el token pasado
	 * para buscar no existe lanza una excepcion.
	 * @param subChain
	 *            String usado para buscar
	 * @param token
	 *            token usado para buscar
	 * @throws UnexistingTokenException
	 *             si el token no existe
	 * @return List<Contenido> con los contenidos de la busqueda.
	 * */
	@Override
	public final List<Contenido> buscar(final String subChain,
			final String token)
			throws UnexistingTokenException {
		List<Contenido> resultado = super.buscar(subChain, token);
		if (resultado.isEmpty() && (!(this.servidorRespaldo == null))) {
			return servidorRespaldo.buscar(subChain, token);
		} else {
			return resultado;
			}

	}

}
