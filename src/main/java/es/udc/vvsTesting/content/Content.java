package es.udc.vvsTesting.content;

import java.util.List;

public interface Content {

	String obtenerTitulo();

	int obtenerDuracion();

	List<Content> obtenerListaReproduccion();

	List<Content> buscar(String subChain);

	void agregar(Content content, Content predecesor);

	void eliminar(Content content);

}
