package es.udc.vvsTesting;

import java.util.List;

import es.udc.vvsTesting.content.Anuncio;
import es.udc.vvsTesting.content.Content;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    public void testAnuncio(){
    	//test atributos anuncio devuelven los que deben
    	//Creamos anuncios
    	Anuncio anuncio1 = new Anuncio();
    	Anuncio anuncio2 = new Anuncio();
    	//Comprobamos que el titulo es PUBLICIDAD
    	assertEquals(anuncio1.obtenerTitulo(),"PUBLICIDAD");
    	//Comprobamos que la duracion es 5
    	assertEquals(anuncio1.obtenerDuracion(),5);
    	//Comprobamos que al obtener la lista de Reproduccion se obtenga una lista con un elemento el anuncio
    	List<Content> lista = anuncio1.obtenerListaReproduccion();
    	assertEquals(lista.size(),1);
    	for(int i =0;i<lista.size();i++){
    		assertEquals(lista.get(i),anuncio1);
    	}
    	//Comprobamos que al buscar por String se devuelva una lista con un elemento o ninguno
    	lista=null;
    	//Comprobamos que devuelve una lista vacia si no se pasa un String con el titulo exacto
    	lista = anuncio1.buscar("PUBLI");
    	assertEquals(lista.size(),0);
    	//Comprobamos que devuelve una lista con un elemento si se pasa un String con el titulo exacto
    	lista=null;
    	lista=anuncio1.buscar("PUBLICIDAD");
    	assertEquals(lista.size(),1);
    	for(int i =0;i<lista.size();i++){
    		assertEquals(lista.get(i),anuncio1);
    	}
    }
}
