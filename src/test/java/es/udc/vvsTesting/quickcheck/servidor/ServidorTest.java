package es.udc.vvsTesting.quickcheck.servidor;

import static net.java.quickcheck.generator.CombinedGenerators.lists;
import static net.java.quickcheck.generator.PrimitiveGenerators.integers;
import static net.java.quickcheck.generator.PrimitiveGenerators.strings;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.PrimitiveGenerators;
import net.java.quickcheck.generator.iterable.Iterables;

import org.junit.Test;

import es.udc.vvsTesting.contenido.Cancion;
import es.udc.vvsTesting.contenido.Contenido;
import es.udc.vvsTesting.contenido.Emisora;
import es.udc.vvsTesting.servidor.Servidor;
import es.udc.vvsTesting.servidor.ServidorSimple;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.InvalidSongsDurationException;

public class ServidorTest {
	@Test
	public void nombreServidorTest(){
		 Generator<String> first = PrimitiveGenerators.strings();
		 List<String> lista = new ArrayList<String>();
		 List<Servidor> servidores = new ArrayList<Servidor>();
		 String tokenAdmin = "4691819800";
		 Servidor servidor;
		 for(int i=0;i<100;i++){
			 lista.add(first.next());
		 }
		 for(int i=0;i<100;i++){
			 servidor = new ServidorSimple(lista.get(i),tokenAdmin);
			 servidores.add(servidor);
		 }
		 for(int i=0;i<100;i++){
			 assertEquals(servidores.get(i).obtenerNombre(),lista.get(i));
		 }
	}
	
	@Test
	public void altaServidorTest(){
		String token=null;
		boolean alta=false;
		for (Servidor servidor : Iterables.toIterable(new ServidorGenerator())){
			token= servidor.alta();
			if(token != null){
				alta=true;
			}
			assertTrue(alta);
			alta=false;
		}
	    		
	}
	//Genera servidores
	class ServidorGenerator implements Generator<Servidor>{
		String tokenAdmin = "4691819800";
		ContenidoGenerator c = new ContenidoGenerator();
		Contenido contenido = c.next();
		public Servidor next(){
			Servidor servidor = new ServidorSimple(strings().next(),tokenAdmin);
			try {
				servidor.agregar(contenido, tokenAdmin);
			} catch (InsufficientPermissionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return servidor;
		}
	}
	//Genera Emisoras
	class ContenidoGenerator implements Generator<Contenido> {
		Generator<List<String>> lGen = lists(strings());

		public Contenido next() {
		    List<String> lista = lGen.next();
	    	Generator<Integer> iGen = integers(1,100);
			Emisora emisora=new Emisora(strings().next());
			for (String any : lista) { 
				Cancion cancion;
				try {
					cancion = new Cancion(any, iGen.next());
					emisora.agregar(cancion, null);
				} catch (InvalidSongsDurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
			} 	
			return emisora;		
		}	
	}
}
