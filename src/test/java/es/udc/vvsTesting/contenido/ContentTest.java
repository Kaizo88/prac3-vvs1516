package es.udc.vvsTesting.contenido;

import java.util.List;

import es.udc.vvsTesting.contenido.Anuncio;
import es.udc.vvsTesting.contenido.Cancion;
import es.udc.vvsTesting.contenido.Contenido;
import es.udc.vvsTesting.contenido.Emisora;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ContentTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ContentTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ContentTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	public void testAnuncio() {
		// test atributos anuncio devuelven los que deben
		// Creamos anuncios
		Anuncio anuncio1 = new Anuncio();
		Anuncio anuncio2 = new Anuncio();
		// Anuncio anuncio2 = new Anuncio();
		// Comprobamos que el titulo es PUBLICIDAD
		assertEquals(anuncio1.obtenerTitulo(), "PUBLICIDAD");
		// Comprobamos que la duracion es 5
		assertEquals(anuncio1.obtenerDuracion(), 5);
		// Comprobamos que al obtener la lista de Reproduccion se obtenga una
		// lista con un elemento el anuncio
		List<Contenido> lista = anuncio1.obtenerListaReproduccion();
		assertEquals(lista.size(), 1);
		for (int i = 0; i < lista.size(); i++) {
			assertEquals(lista.get(i), anuncio1);
		}
		// Comprobamos que al buscar por String se devuelva una lista con un
		// elemento o ninguno
		lista = null;
		// Comprobamos que devuelve una lista vacia si no se pasa un String con
		// el titulo exacto
		lista = anuncio1.buscar("PUBLI");
		assertEquals(lista.size(), 0);
		// Comprobamos que devuelve una lista con un elemento si se pasa un
		// String con el titulo exacto
		lista = null;
		lista = anuncio1.buscar("PUBLICIDAD");
		assertEquals(lista.size(), 1);
		for (int i = 0; i < lista.size(); i++) {
			assertEquals(lista.get(i), anuncio1);
		}
		// agregar
		lista = null;
		boolean catched = false;
		try {
			anuncio1.agregar(anuncio2, null);
		} catch (Exception e) {
			catched = true;
		}
		assertTrue(catched);
		catched = false;
		assertEquals(anuncio1.obtenerDuracion(), 5);
		assertEquals(anuncio1.obtenerTitulo(), "PUBLICIDAD");
		lista = anuncio1.buscar("PUBLICIDAD");
		assertEquals(lista.size(), 1);
		for (int i = 0; i < lista.size(); i++) {
			assertEquals(lista.get(i), anuncio1);
		}
		// eliminar
		lista = null;
		try {
			anuncio1.eliminar(anuncio2);
		} catch (Exception e) {
			catched = true;
		}
		assertTrue(catched);
		catched = false;
		assertEquals(anuncio1.obtenerDuracion(), 5);
		assertEquals(anuncio1.obtenerTitulo(), "PUBLICIDAD");
		lista = anuncio1.buscar("PUBLICIDAD");
		assertEquals(lista.size(), 1);
		for (int i = 0; i < lista.size(); i++) {
			assertEquals(lista.get(i), anuncio1);
		}

	}

	public void testCancion() {
		// Creamos Canciones
		Cancion cancion1 = new Cancion("cancion1", 1);
		Cancion cancion2 = new Cancion("cancion2", 2);
		// Comprobamos titulos
		assertEquals(cancion1.obtenerTitulo(), "cancion1");
		assertEquals(cancion2.obtenerTitulo(), "cancion2");
		// Comprobamos duracion
		assertEquals(cancion1.obtenerDuracion(), 1);
		assertEquals(cancion2.obtenerDuracion(), 2);
		// Comprobamos lista de Reproduccion
		List<Contenido> lista1 = cancion1.obtenerListaReproduccion();
		List<Contenido> lista2 = cancion2.obtenerListaReproduccion();

		assertEquals(lista1.size(), 1);
		assertEquals(lista2.size(), 1);
		for (int i = 0; i < lista1.size(); i++) {
			assertEquals(lista1.get(i), cancion1);
		}
		for (int i = 0; i < lista2.size(); i++) {
			assertEquals(lista2.get(i), cancion2);
		}
		lista1 = null;
		lista2 = null;

		// Comprobamos buscar
		lista1 = cancion1.buscar("can");
		assertEquals(lista1.size(), 1);
		lista1 = null;
		lista1 = cancion1.buscar("cancion1");
		for (int i = 0; i < lista1.size(); i++) {
			assertEquals(lista1.get(i), cancion1);
		}
		// agregar
		boolean catched = false;
		lista1 = null;
		try {
			cancion1.agregar(cancion2, null);
		} catch (Exception e) {
			catched = true;
		}
		assertTrue(catched);
		catched = false;
		assertEquals(cancion1.obtenerDuracion(), 1);
		assertEquals(cancion1.obtenerTitulo(), "cancion1");
		lista1 = cancion1.buscar("can");
		assertEquals(lista1.size(), 1);
		for (int i = 0; i < lista1.size(); i++) {
			assertEquals(lista1.get(i), cancion1);
		}
		// eliminar
		lista1 = null;
		try {
			cancion1.agregar(cancion1, null);
		} catch (Exception e) {
			catched = true;
		}
		assertTrue(catched);
		catched = false;
		assertEquals(cancion1.obtenerDuracion(), 1);
		assertEquals(cancion1.obtenerTitulo(), "cancion1");
		lista1 = cancion1.buscar("can");
		assertEquals(lista1.size(), 1);
		for (int i = 0; i < lista1.size(); i++) {
			assertEquals(lista1.get(i), cancion1);
		}

	}

	public void testEmisora() {
		// Creamos emisora
		Emisora emisora1 = new Emisora("emisora1");
		// Creamos canciones y publicidad
		Cancion cancion1 = new Cancion("cancion1", 1);
		Cancion cancion2 = new Cancion("cancion2", 2);
		Anuncio anuncio = new Anuncio();
		// Comprobamos titulo emisora
		assertEquals(emisora1.obtenerTitulo(), "emisora1");
		// Comprobamos duracion = 0
		assertEquals(emisora1.obtenerDuracion(), 0);
		// Insertamos canciones y anuncios
		emisora1.agregar(cancion1, null);// insertamos al principio
		assertEquals(emisora1.obtenerDuracion(), 1);
		emisora1.agregar(anuncio, null);// insertamos al principio
		assertEquals(emisora1.obtenerDuracion(), 6);
		emisora1.agregar(anuncio, anuncio);// insertamos despues de anuncio
		assertEquals(emisora1.obtenerDuracion(), 11);
		emisora1.agregar(cancion1, cancion1);// insertamos despues de cancion1
		assertEquals(emisora1.obtenerDuracion(), 12);
		emisora1.agregar(cancion2, null);// insertamos al principio
		assertEquals(emisora1.obtenerDuracion(), 14);
		// Comprobamos que se hayan insertado en el orden correcto
		assertEquals(emisora1.obtenerListaReproduccion().get(0), cancion2);
		assertEquals(emisora1.obtenerListaReproduccion().get(1), anuncio);
		assertEquals(emisora1.obtenerListaReproduccion().get(2), anuncio);
		assertEquals(emisora1.obtenerListaReproduccion().get(3), cancion1);
		assertEquals(emisora1.obtenerListaReproduccion().get(4), cancion1);
		// Borramos duplicados
		emisora1.eliminar(anuncio);
		assertEquals(emisora1.obtenerDuracion(), 9);
		emisora1.eliminar(cancion1);
		assertEquals(emisora1.obtenerDuracion(), 8);
		assertEquals(emisora1.obtenerListaReproduccion().get(0), cancion2);
		assertEquals(emisora1.obtenerListaReproduccion().get(1), anuncio);
		assertEquals(emisora1.obtenerListaReproduccion().get(2), cancion1);
		// buscar
		List<Contenido> lista = emisora1.buscar("PUBLI");
		assertEquals(lista.size(), 1);
		lista = null;
		lista = emisora1.buscar("cancion1");
		assertEquals(lista.size(), 1);
		assertEquals(lista.get(0).obtenerTitulo(), "cancion1");
		lista = null;
		lista = emisora1.buscar("cancion");
		assertEquals(lista.size(), 2);
		assertEquals(lista.get(0), cancion2);
		assertEquals(lista.get(1), cancion1);
		// Borramos todo de la emisora e intentamos borrar
		lista = null;
		lista = emisora1.obtenerListaReproduccion();
		emisora1.eliminar(cancion1);
		emisora1.eliminar(cancion2);
		emisora1.eliminar(anuncio);

		lista = null;
		lista = emisora1.obtenerListaReproduccion();
		assertEquals(lista.size(), 0);

		emisora1.eliminar(anuncio);
		lista = null;
		lista = emisora1.obtenerListaReproduccion();
		assertEquals(lista.size(), 0);

		emisora1.eliminar(cancion1);
		lista = null;
		lista = emisora1.obtenerListaReproduccion();
		assertEquals(lista.size(), 0);

		// Agregar despues del predecesor si lista vacia
		// lo inserta sin mas al principio de la lista
		emisora1.agregar(cancion1, cancion2);
		lista = null;
		lista = emisora1.obtenerListaReproduccion();
		assertEquals(lista.size(), 1);
		assertEquals(lista.get(0).obtenerTitulo(), "cancion1");

	}
}
