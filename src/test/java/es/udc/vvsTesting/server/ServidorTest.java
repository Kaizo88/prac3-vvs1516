package es.udc.vvsTesting.server;

import java.util.List;

import junit.framework.TestCase;
import es.udc.vvsTesting.content.Anuncio;
import es.udc.vvsTesting.content.Cancion;
import es.udc.vvsTesting.content.Content;
import es.udc.vvsTesting.content.Emisora;
import es.udc.vvsTesting.utils.ContentNotFoundException;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.SearchLimitReachedException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

public class ServidorTest extends TestCase {

	public void testObtenerNombre() {
		Server server = new Servidor("Prueba", "4691819800");
		assertEquals("Prueba", server.obtenerNombre());
	}

	public void testAlta() {
		Servidor server = new Servidor("Prueba", "4691819800");
		assertTrue(server.getTokens().isEmpty());
		String token = server.alta();
		assertTrue(server.getTokens().containsKey(token));
		assertEquals(10, server.getTokens().get(token).intValue());
	}

	public void testBaja() {
		Boolean except = false;
		Servidor server = new Servidor("Prueba", "4691819800");
		assertTrue(server.getTokens().isEmpty());
		String token = server.alta();
		assertTrue(!server.getTokens().isEmpty());
		try {
			server.baja(token + "45");
		} catch (UnexistingTokenException e) {
			except = true;
		}
		assertTrue(except);
		except = false;
		try {
			server.baja(token);
		} catch (UnexistingTokenException e) {
			except = true;
		}
		assertFalse(except);
		assertFalse(server.getTokens().containsKey(token));
	}

	public void testAgregar() {
		String tokenAdmin = "4691819800";
		Boolean except = false;
		Servidor server = new Servidor("Prueba", tokenAdmin);
		Content content = new Anuncio();
		try {
			server.agregar(content, tokenAdmin);
		} catch (InsufficientPermissionsException e) {
			except = true;
		}
		assertFalse(except);
		assertEquals(1, server.getContentList().size());
		Content content2 = new Anuncio();
		try {
			server.agregar(content2, tokenAdmin + "123");
		} catch (InsufficientPermissionsException e) {
			except = true;
		}
		assertTrue(except);
		assertEquals(1, server.getContentList().size());

	}

	public void testEliminar() {
		String tokenAdmin = "4691819800";
		Boolean except1 = false;
		Boolean except2 = false;
		Servidor server = new Servidor("Prueba", tokenAdmin);
		Content content = new Anuncio();
		try {
			server.agregar(content, tokenAdmin);
		} catch (InsufficientPermissionsException e) {
			except1 = true;
		}
		assertFalse(except1);

		except1 = false;
		try {
			server.eliminar(content, tokenAdmin + "123");
		} catch (InsufficientPermissionsException e) {
			except1 = true;
		} catch (ContentNotFoundException e) {
			except2 = true;
		}

		assertTrue(except1);
		assertFalse(except2);
		except2 = false;
		except1 = false;

		try {
			server.eliminar(content, tokenAdmin);
		} catch (InsufficientPermissionsException e) {
			except1 = true;
		} catch (ContentNotFoundException e) {
			except2 = true;
		}
		assertFalse(except1);
		assertFalse(except2);
		assertEquals(0, server.getContentList().size());

		except1 = false;
		except2 = false;
		try {
			server.eliminar(content, tokenAdmin);
		} catch (InsufficientPermissionsException e) {
			except1 = true;
		} catch (ContentNotFoundException e) {
			except2 = true;
		}
		assertFalse(except1);
		assertTrue(except2);
		assertEquals(0, server.getContentList().size());
	}

	public void testBuscar() throws InsufficientPermissionsException,
			UnexistingTokenException, SearchLimitReachedException {
		String tokenAdmin = "4691819800";
		Servidor server = new Servidor("Prueba", tokenAdmin);
		Servidor serverPrueba = new Servidor("Vacio", tokenAdmin);
		String token = server.alta();
		String tokenPrueba = serverPrueba.alta();
		Content anuncio = new Anuncio();
		Content cancion1 = new Cancion("cancion1", 6);
		Content cancion2 = new Cancion("cancion2", 8);
		Content cancion3 = new Cancion("cancion3", 10);
		Content emisora1 = new Emisora("emisora1");
		Content emisora2 = new Emisora("emisora2");
		boolean exceptionLimit = false;
		// Agregamos emisoras y canciones
		// Emisora1
		emisora1.agregar(cancion1, null);
		emisora1.agregar(cancion2, cancion1);
		// Emisora2
		emisora2.agregar(cancion1, null);
		emisora2.agregar(cancion2, null);
		emisora2.agregar(cancion3, null);
		emisora2.agregar(cancion1, null);
		emisora2.agregar(cancion2, null);
		emisora2.agregar(cancion1, null);
		emisora2.agregar(cancion2, null);

		server.agregar(cancion1, tokenAdmin);
		server.agregar(cancion2, tokenAdmin);
		server.agregar(emisora1, tokenAdmin);

		// Buscar en un servidor vacio
		// Si token registrado
		List<Content> lista = serverPrueba.buscar("", tokenPrueba);
		assertEquals(lista.size(), 0);
		// Si token no registrado
		lista = null;
		lista = serverPrueba.buscar("", "0");
		assertEquals(lista.size(), 1);
		assertEquals(lista.get(0).obtenerTitulo(), "PUBLICIDAD");

		// Buscar en un servidor con canciones
		// Si token registrado
		lista = null;
		lista = server.buscar("cancion", token);
		assertEquals(lista.size(), 4);
		assertEquals(lista.get(0), cancion1);
		assertEquals(lista.get(1), cancion2);
		// Si token no registrado
		lista = null;
		lista = server.buscar("cancion", "0");
		assertEquals(lista.size(), 6);
		assertEquals(lista.get(0).obtenerTitulo(), "PUBLICIDAD");
		assertEquals(lista.get(4).obtenerTitulo(), "PUBLICIDAD");
		// Crear nuevo token buscar 11 contenidos
		String token2 = server.alta();
		server.agregar(emisora2, tokenAdmin);
		lista = null;
		lista = server.buscar("cancion1", token2);
		// Busco 5 el token aun es valido
		assertEquals(lista.size(), 5);
		for (int i = 0; i < lista.size(); i++)
			assertEquals(lista.get(i).obtenerTitulo(), "cancion1");
		// Busco 5 el token aun es valido
		lista = null;
		lista = server.buscar("cancion2", token2);
		assertEquals(lista.size(), 5);
		for (int i = 0; i < lista.size(); i++)
			assertEquals(lista.get(i).obtenerTitulo(), "cancion2");
		// Busco 1 mas el token ya es invalido
		lista = null;
		lista = server.buscar("cancion3", token2);
		assertEquals(lista.size(), 2);
		assertEquals(lista.get(0).obtenerTitulo(), "PUBLICIDAD");
	}

}
