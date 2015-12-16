package es.udc.vvsTesting.contenido.mocks;

import java.util.ArrayList;
import java.util.List;

import es.udc.vvsTesting.contenido.Contenido;

public class CancionMock implements Contenido{

	private String titulo;
	private int duracion;
	private ArrayList<Contenido> listaReproduccion;
	
	@Override
	public String obtenerTitulo() {
		return titulo;
	}

	@Override
	public int obtenerDuracion() {
		return duracion;
	}

	@Override
	public List<Contenido> obtenerListaReproduccion() {
		return listaReproduccion;
	}

	@Override
	public List<Contenido> buscar(String subCadena) {
		return listaReproduccion;
	}

	@Override
	public void agregar(Contenido content, Contenido predecesor) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void eliminar(Contenido content) {
		throw new UnsupportedOperationException();
	}

}
