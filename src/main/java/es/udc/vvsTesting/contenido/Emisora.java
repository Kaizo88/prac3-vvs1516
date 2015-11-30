package es.udc.vvsTesting.contenido;

import java.util.ArrayList;
import java.util.List;

public class Emisora implements Contenido {
	private String titulo;
	private int duracion;
	private List<Contenido> listaReproduccion;

	public Emisora(String titulo) {
		this.titulo = titulo;
		this.duracion = 0;
		this.listaReproduccion = new ArrayList<Contenido>();
	}

	public String obtenerTitulo() {
		return titulo;
	}

	public int obtenerDuracion() {
		return duracion;
	}

	public List<Contenido> obtenerListaReproduccion() {
		return listaReproduccion;
	}

	public List<Contenido> buscar(String subCadena) {
		List<Contenido> lista = new ArrayList<Contenido>();
		for (int i = 0; i < listaReproduccion.size(); i++) {
			if (contieneCadena(listaReproduccion.get(i).obtenerTitulo(),
					subCadena)) {
				lista.add(listaReproduccion.get(i));
			}
		}
		return lista;
	}

	public void agregar(Contenido content, Contenido predecesor) {
		if (predecesor == null) {// insertar por el principio
			if (listaReproduccion.isEmpty()) {
				listaReproduccion.add(content);
				this.duracion = this.duracion + content.obtenerDuracion();
			} else {
				listaReproduccion.add(0, content);
				this.duracion = this.duracion + content.obtenerDuracion();
			}
		} else {// Sino insertar despues de predecesor
			List<Contenido> listaAux = new ArrayList<Contenido>();
			int i = 0;
				while (i < listaReproduccion.size()) {
					listaAux.add(listaReproduccion.get(i));
					if (listaReproduccion.get(i)
							.equals(predecesor)) {
						listaAux.add(content);
						this.duracion = this.duracion + content.obtenerDuracion();
					}
					i++;
				}
				// 
				listaReproduccion = listaAux;
		}

		/*
		 * Si hay canciones repetidas agregamos después de todas las
		 * predecesoras 
		 */
	}

	public void eliminar(Contenido content) {
		if (listaReproduccion.isEmpty()) {
			return;
		} else {
			int contains = 0;
			for (int i = 0; i < listaReproduccion.size(); i++) {
				if (listaReproduccion.get(i).equals(content)) {
					contains++;
				}
			}
			while (contains != 0) {
				listaReproduccion.remove(content);
				this.duracion = this.duracion - content.obtenerDuracion();
				contains--;
			}
		}
	}

	private boolean contieneCadena(String str1, String str2) {
		if (str1.toLowerCase().contains(str2.toLowerCase()))
			return true;
		else
			return false;
	}
}
