package es.udc.vvsTesting.server;

import junit.framework.TestCase;

import org.junit.Test;

import es.udc.vvsTesting.content.Anuncio;
import es.udc.vvsTesting.content.Content;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

public class ServidorTest extends TestCase{

	@Test
	public void testObtenerNombre() {
		Server server = new Servidor("Prueba", "4691819800");
		assertEquals("Prueba", server.obtenerNombre());
	}

	@Test
	public void testAlta() {
		Servidor server = new Servidor("Prueba", "4691819800");
		assertTrue(server.getTokens().isEmpty());
		String token = server.alta();
		assertTrue(server.getTokens().containsKey(token));
		assertEquals(10, server.getTokens().get(token).intValue());
	}

	@Test
	public void testBaja() {
		Boolean except = false;
		Servidor server = new Servidor("Prueba", "4691819800");
		assertTrue(server.getTokens().isEmpty());
		String token = server.alta();
		assertTrue(!server.getTokens().isEmpty());
		try {
			server.baja(token+"45");
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

	@Test
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
			server.agregar(content2, tokenAdmin+"123");
		} catch (InsufficientPermissionsException e) {
			except = true;
		}
		assertTrue(except);
		assertEquals(1, server.getContentList().size());

	}

	@Test
	public void testEliminar() {
		//fail("Not yet implemented");
	}

}
