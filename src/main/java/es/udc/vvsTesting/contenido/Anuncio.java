package es.udc.vvsTesting.contenido;

import java.util.ArrayList;
import java.util.List;

public class Anuncio implements Contenido {
	private String titulo;
	private int duracion;

	public Anuncio() {
		this.titulo = "PUBLICIDAD";
		this.duracion = 5;
	}

	public String obtenerTitulo() {
		return titulo;
	}

	public int obtenerDuracion() {
		return duracion;
	}

	public List<Contenido> obtenerListaReproduccion() {
		List<Contenido> lista = new ArrayList<Contenido>();
		lista.add(this);
		return lista;
	}

	public List<Contenido> buscar(String subChain) {
		List<Contenido> lista = new ArrayList<Contenido>();
		if (subChain == titulo)
			lista.add(this);
		return lista;
	}

	public void agregar(Contenido content, Contenido predecesor) {

	}

	public void eliminar(Contenido content) {

	}

}
