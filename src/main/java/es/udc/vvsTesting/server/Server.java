package es.udc.vvsTesting.server;

import es.udc.vvsTesting.content.Content;

public interface Server {

	String obtenerNombre();
	
	String alta();
	
	String baja(String token);
	
	void agregar(Content content,String token);
	
	void eliminar(Content content,String token);
	
	Content buscar(String subChain, String token);
	
	
	
}
