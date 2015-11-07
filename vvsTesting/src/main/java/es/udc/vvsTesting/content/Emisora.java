package es.udc.vvsTesting.content;

import java.util.ArrayList;
import java.util.List;

public class Emisora implements Content {
	private String titulo;
	private int duracion;
	private List<Content> listaReproduccion;
	
	public Emisora(String titulo) {
		this.titulo = titulo;
		this.duracion = 0;
		this.listaReproduccion=new ArrayList<Content>();
	}

	public String obtenerTitulo() {
		return titulo;
	}

	public int obtenerDuracion() {
		return duracion;
	}

	public List<Content> obtenerListaReproduccion() {
		return listaReproduccion;
	}

	public List<Content> buscar(String subChain) {
		List<Content> lista=new ArrayList<Content>();
		for(Content content:listaReproduccion)
			if (content.obtenerTitulo()==subChain) {
				if (!lista.contains(content))
					lista.add(this);}
		return lista;
	}

	public void agregar(Content content, Content predecesor) {
		if (listaReproduccion.isEmpty()) {
			listaReproduccion.add(content);
			this.duracion=content.obtenerDuracion();
		}
		
		/*Si hay canciones repetidas agregamos después de todas las predecesoras ??*/
	}

	public void eliminar(Content content) {
		listaReproduccion.remove(content);
	}	
	
}
