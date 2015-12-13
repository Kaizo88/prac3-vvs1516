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
		if (subChain.equalsIgnoreCase(titulo))
			lista.add(this);
		return lista;
	}

	public void agregar(Contenido content, Contenido predecesor) {
		throw new UnsupportedOperationException("Agregar no soportado por Anuncio");
	}

	public void eliminar(Contenido content) {
		throw new UnsupportedOperationException("Eliminar no soportado por Anuncio");	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duracion;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anuncio other = (Anuncio) obj;
		if (duracion != other.duracion)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

}
