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
		if (predecesor==null){//insertar por el principio
			if (listaReproduccion.isEmpty()) {
				listaReproduccion.add(content);
				this.duracion=this.duracion+content.obtenerDuracion();
			}
			else{
				listaReproduccion.add(0, content);
				this.duracion=this.duracion+content.obtenerDuracion();
			}
		}
		else{//Sino insertar despues de predecesor
			List<Content> listaAux = new ArrayList<Content>();
			int i=0;
			if (listaReproduccion.isEmpty()) {
				listaReproduccion.add(content);
				this.duracion=this.duracion+content.obtenerDuracion();
			}
			else{
				i=0;
				while(i<listaReproduccion.size()){
					listaAux.add(listaReproduccion.get(i));
					if(listaReproduccion.get(i).obtenerTitulo().equals(predecesor.obtenerTitulo())){
						listaAux.add(content);
					}
					i++;
				}
				listaReproduccion=listaAux;
				this.duracion=this.duracion+content.obtenerDuracion();
			}
		}
		
		
		/*Si hay canciones repetidas agregamos después de todas las predecesoras ??*/
	}

	public void eliminar(Content content) {
		if(listaReproduccion.isEmpty()){
			listaReproduccion=null;
		}
		else{
			int contains=0;
			for(int i=0;i<listaReproduccion.size();i++){
				if(listaReproduccion.get(i).equals(content)){
					contains++;
				}
			}
			if(contains != 0){
				listaReproduccion.remove(content);
				this.duracion=this.duracion-content.obtenerDuracion();
			}
		}
	}	
	
}
