package es.udc.vvsTesting.content;

import java.util.ArrayList;
import java.util.List;

public class Cancion implements Content {

	private String titulo;
	private int duracion;

	public Cancion(String titulo, int duracion) {
		this.titulo = titulo;
		this.duracion = duracion;
	}

	public String obtenerTitulo() {
		return titulo;
	}

	public int obtenerDuracion() {
		return duracion;
	}

	public List<Content> obtenerListaReproduccion() {
		List<Content> lista = new ArrayList<Content>();
		lista.add(this);
		return lista;
	}

	public List<Content> buscar(String subChain) {
		List<Content> lista = new ArrayList<Content>();
		if (contieneCadena(titulo, subChain))
			lista.add(this);
		return lista;
	}

	public void agregar(Content content, Content predecesor) {

	}

	public void eliminar(Content content) {

	}

	private boolean contieneCadena(String str1, String str2) {
		if (str1.toLowerCase().contains(str2.toLowerCase()))
			return true;
		else
			return false;
	}
}
