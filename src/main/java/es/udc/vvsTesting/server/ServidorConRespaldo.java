package es.udc.vvsTesting.server;

import java.util.List;

import es.udc.vvsTesting.content.Content;
import es.udc.vvsTesting.utils.SearchLimitReachedException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

public class ServidorConRespaldo extends Servidor{

	public Server servidorRespaldo;
	
	public ServidorConRespaldo(String nombre, String tokenAdmin, Server serverRespaldo) {
		super(nombre, tokenAdmin);
		this.servidorRespaldo = serverRespaldo;
	}
	
	@Override
	public List<Content> buscar(String subChain, String token)
			throws UnexistingTokenException, SearchLimitReachedException {
		List<Content> resultado = super.buscar(subChain,token);
		if(resultado.isEmpty()){
			return servidorRespaldo.buscar(subChain,token);
		}
		else return resultado;
			
	}

}
