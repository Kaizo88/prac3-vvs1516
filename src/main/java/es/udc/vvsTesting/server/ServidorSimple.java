package es.udc.vvsTesting.server;

public class ServidorSimple extends Servidor{

	public Server servidorRespaldo;
	
	public ServidorSimple(String nombre, String tokenAdmin) {
		super(nombre, tokenAdmin);
	}	
}
