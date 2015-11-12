package es.udc.vvsTesting.servidor;

import java.util.List;

import es.udc.vvsTesting.contenido.Contenido;
import es.udc.vvsTesting.utils.ContentNotFoundException;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

public interface Servidor {

	String obtenerNombre();

	String alta();

	void baja(String token) throws UnexistingTokenException;

	void agregar(Contenido content, String token)
			throws InsufficientPermissionsException;

	void eliminar(Contenido content, String token)
			throws InsufficientPermissionsException, ContentNotFoundException;

	List<Contenido> buscar(String subChain, String token)
			throws UnexistingTokenException;

}
