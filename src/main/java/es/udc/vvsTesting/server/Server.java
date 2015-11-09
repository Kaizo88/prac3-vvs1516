package es.udc.vvsTesting.server;

import es.udc.vvsTesting.content.Content;
import es.udc.vvsTesting.utils.ContentNotFoundException;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

public interface Server {

	String obtenerNombre();
	
	String alta();
	
	void baja(String token) throws UnexistingTokenException;
	
	void agregar(Content content,String token) throws InsufficientPermissionsException;
	
	void eliminar(Content content,String token) throws InsufficientPermissionsException, ContentNotFoundException;
	
	Content buscar(String subChain, String token);
	
	
	
}
