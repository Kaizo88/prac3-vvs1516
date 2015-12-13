package es.udc.vvsTesting.contenido;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.udc.vvsTesting.utils.InvalidSongsDurationException;

public class Cancion implements Contenido{
	private String titulo;
	private int duracion;

	public Cancion(String titulo, int duracion) throws InvalidSongsDurationException {
		if(duracion>0){
		this.titulo = titulo;
		this.duracion = duracion;
		}else{
			throw new InvalidSongsDurationException(duracion);
		}
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
		throw new UnsupportedOperationException("Agregar no soportado por Cancion");
	}

	public void eliminar(Contenido content) {
		throw new UnsupportedOperationException("Eliminar no soportado por Cancion");
	}

	private boolean contieneCadena(String str1, String str2) {
		if (str1.toLowerCase(new Locale("es", "ES")).contains(str2.toLowerCase(new Locale("es", "ES"))))
			return true;
		else
			return false;
	}

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
		Cancion other = (Cancion) obj;
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
