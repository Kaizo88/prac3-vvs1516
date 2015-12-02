package es.udc.vvsTesting.servidor;

import java.util.List;

import es.udc.vvsTesting.contenido.Contenido;
import es.udc.vvsTesting.utils.UnexistingTokenException;

public class ServidorConRespaldo extends ServidorImp {

	public Servidor servidorRespaldo;

	public ServidorConRespaldo(String nombre, String tokenAdmin,
			Servidor serverRespaldo) {
		super(nombre, tokenAdmin);
		this.servidorRespaldo = serverRespaldo;
	}

	@Override
	public List<Contenido> buscar(String subChain, String token)
			throws UnexistingTokenException {
		List<Contenido> resultado = super.buscar(subChain, token);
		if (resultado.isEmpty() && (!(this.servidorRespaldo == null))) {
			return servidorRespaldo.buscar(subChain, token);
		} else
			return resultado;

	}

}
