package es.udc.vvsTesting.contenido;

import java.util.ArrayList;
import java.util.List;

public class Cancion implements Contenido {

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

	public List<Contenido> obtenerListaReproduccion() {
		List<Contenido> lista = new ArrayList<Contenido>();
		lista.add(this);
		return lista;
	}

	public List<Contenido> buscar(String subChain) {
		List<Contenido> lista = new ArrayList<Contenido>();
		if (contieneCadena(titulo, subChain))
			lista.add(this);
		return lista;
	}

	public void agregar(Contenido content, Contenido predecesor) {

	}

	public void eliminar(Contenido content) {

	}

	private boolean contieneCadena(String str1, String str2) {
		if (str1.toLowerCase().contains(str2.toLowerCase()))
			return true;
		else
			return false;
	}
}
