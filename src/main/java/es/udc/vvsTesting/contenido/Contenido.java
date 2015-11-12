package es.udc.vvsTesting.contenido;

import java.util.List;

public interface Contenido {

	String obtenerTitulo();

	int obtenerDuracion();

	List<Contenido> obtenerListaReproduccion();

	List<Contenido> buscar(String subCadena);

	void agregar(Contenido content, Contenido predecesor);

	void eliminar(Contenido content);

}
