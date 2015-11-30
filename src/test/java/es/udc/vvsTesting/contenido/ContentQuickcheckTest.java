package es.udc.vvsTesting.contenido;


import java.util.List;

import org.junit.Test;

import net.java.quickcheck.Generator;
import net.java.quickcheck.characteristic.Classification;
import net.java.quickcheck.generator.iterable.Iterables;
import static net.java.quickcheck.generator.PrimitiveGenerators.integers;
import static net.java.quickcheck.generator.PrimitiveGenerators.strings;
import static net.java.quickcheck.generator.CombinedGenerators.lists;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ContentQuickcheckTest  {
	
	private int contarApariciones(Contenido emisora,Contenido contenido){
		int cont=0;
		for (Contenido aux:emisora.obtenerListaReproduccion()) {
			if (contenido.equals(aux)) {
				cont++;
			}
		}
		return cont;
	}
	
	@Test
	public void agregarConPrecedenteNuloTest() {
		
    	Classification c = new Classification();
		for (Contenido contenido : Iterables.toIterable(new ContenidoGenerator())){
			
    	    Integer anyNumber = integers(0,100).next();
			String titulo=strings("abc",1,4).next();
			Cancion cancion=new Cancion(titulo, anyNumber);
			if (contenido.obtenerListaReproduccion().isEmpty()) {
				c.classifyCall("lista vacía");
			}
    	    else {
    		c.classifyCall("lista no vacía");
    	    }

			int esperado=cancion.obtenerDuracion()+contenido.obtenerDuracion();
			contenido.agregar(cancion, null);
			assertEquals(esperado,contenido.obtenerDuracion());
			assertEquals(cancion,contenido.obtenerListaReproduccion().get(0));
		}	
    	for (Object cat : c.getCategories()) {
    	    System.out.println("[agregarConPrecedenteNuloTest] ===> " + cat + " => " + c.getFrequency(cat));
    	}
	}
	@Test
	public void agregarConPrecedenteTest() {
    	Classification c = new Classification();		
		for (Contenido contenido : Iterables.toIterable(new ContenidoGenerator2())){
			
    	    Integer anyNumber = integers(1,10).next();
			String titulo=strings("ab",1,2).next();
			Cancion cancion=new Cancion(titulo, anyNumber);
			Cancion precedente=new Cancion(strings("ab",1,2).next(), integers(1,2).next());
			int apariciones=contarApariciones(contenido,precedente);
			if (apariciones==0) {
				c.classifyCall("no precedente");
			}
    	    else  if(apariciones==1){
    		c.classifyCall("un precedente");
    	    } else {
        		c.classifyCall("varios precedentes");
    	    	
    	    }
			int esperado=contenido.obtenerListaReproduccion().size()+apariciones;
			int duracionesperado=apariciones*cancion.obtenerDuracion()+contenido.obtenerDuracion();
			
			contenido.agregar(cancion, precedente);
			
			
			assertEquals(esperado,contenido.obtenerListaReproduccion().size());			
			assertEquals(duracionesperado,contenido.obtenerDuracion());
		}	
    	for (Object cat : c.getCategories()) {
    	    System.out.println("[agregarConPrecedenteTest] ===> " + cat + " => " + c.getFrequency(cat));
    	}
	}

	
	@Test
	public void eliminareTest() {
		Classification c = new Classification();		
		for (Contenido contenido : Iterables.toIterable(new ContenidoGenerator2())){
			
		    Integer anyNumber = integers(1,10).next();
			String titulo=strings("ab",1,2).next();
			Cancion cancion=new Cancion(titulo, anyNumber);
			int apariciones=contarApariciones(contenido,cancion);
			if (apariciones==0) {
				c.classifyCall("ausente");
			}
		    else  if(apariciones==1){
			c.classifyCall("presente una vez");
		    } else {
	    		c.classifyCall("presente varias veces");
		    	
		    }
			int duracionesperado=contenido.obtenerDuracion()-apariciones*cancion.obtenerDuracion();
			
			contenido.eliminar(cancion);
			
			assertFalse(contenido.obtenerListaReproduccion().contains(cancion));		
			assertEquals(duracionesperado,contenido.obtenerDuracion());
		}	
		for (Object cat : c.getCategories()) {
		    System.out.println("[eliminarTest] ===> " + cat + " => " + c.getFrequency(cat));
		}
	}

}



//genera emisora con canciones
class ContenidoGenerator implements Generator<Contenido> {
	Generator<List<String>> lGen = lists(strings());

	public Contenido next() {
	    List<String> lista = lGen.next();
    	Generator<Integer> iGen = integers(0,100);
		Emisora emisora=new Emisora(strings().next());
		for (String any : lista) { 
			Cancion cancion =new Cancion(any, iGen.next());
			emisora.agregar(cancion, null);			
		} 	
		return emisora;		
	}

}
//genera emisora con canciones con pocos variantes(se geneta muchas canciones repetidas)
class ContenidoGenerator2 implements Generator<Contenido> {
	Generator<List<String>> lGen = lists(strings("ab",1,2),0,100);

	public Contenido next() {
	    List<String> lista = lGen.next();
    	Generator<Integer> iGen = integers(1,10);
		Emisora emisora=new Emisora(strings().next());
		for (String any : lista) { 
			Cancion cancion =new Cancion(any, iGen.next());
			emisora.agregar(cancion, null);			
		} 	
		return emisora;		
	}

}