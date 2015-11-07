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
		List<Content> lista=new ArrayList<Content>();
		lista.add(this);
		return lista;
	}

	public List<Content> buscar(String subChain) {
		List<Content> lista=new ArrayList<Content>();
		if (subChain==titulo) 
			lista.add(this);
		return lista;
	}

	public void agregar(Content content, Content predecesor) {
		
	}

	public void eliminar(Content content) {
		
	}	
	
}
