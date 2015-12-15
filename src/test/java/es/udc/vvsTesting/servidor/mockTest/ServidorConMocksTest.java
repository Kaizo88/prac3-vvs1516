package es.udc.vvsTesting.servidor.mockTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;


import es.udc.vvsTesting.contenido.Contenido;
import es.udc.vvsTesting.contenido.mocks.AnuncioMock;
import es.udc.vvsTesting.contenido.mocks.CancionMock;
import es.udc.vvsTesting.contenido.mocks.EmisoraMock;
import es.udc.vvsTesting.servidor.Servidor;
import es.udc.vvsTesting.servidor.ServidorSimple;
import es.udc.vvsTesting.utils.ContentNotFoundException;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

public class ServidorConMocksTest {

	AnuncioMock anuncioMock = Mockito.mock(AnuncioMock.class);
	EmisoraMock emisoraMock = Mockito.mock(EmisoraMock.class);
	CancionMock cancionMock = Mockito.mock(CancionMock.class);
	
	@Test
	public void testMockAgregar(){
		Servidor s1 = new ServidorSimple("serv","42");
		boolean catched = false;
		try{
			s1.agregar(cancionMock, "42");
			}
		catch (Exception e){
			catched = true;
		}
		assertFalse(catched);
		
		try {
			s1.agregar(cancionMock, "44");
		} catch (InsufficientPermissionsException e) {
			catched = true;
		}
		assertTrue(catched);
	}
	
	@Test
	public void testMockEliminar(){
		Servidor s1 = new ServidorSimple("serv","42");
		boolean catched = false;
		boolean catchedB = false;
		try {
			s1.eliminar(cancionMock, "42");
		} catch (InsufficientPermissionsException e) {
			catched = true;
		} catch (ContentNotFoundException e) {
			catchedB = true;
		}
		assertFalse(catched);
		assertTrue(catchedB);
		catched = false;
		catchedB = false;
		try {
			s1.agregar(cancionMock, "42");
		}
		catch (Exception e){
			
		}
		
		try {
			s1.eliminar(cancionMock, "41");
		} catch (InsufficientPermissionsException e) {
			catched = true;
		} catch (ContentNotFoundException e) {
			catchedB = true;
		}
		assertFalse(catchedB);
		assertTrue(catched);
		catched = false;
		catchedB = false;
		
		try {
			s1.eliminar(cancionMock, "42");
		} catch (InsufficientPermissionsException e) {
			catched = true;
		} catch (ContentNotFoundException e) {
			catchedB = true;
		}
		assertFalse(catchedB);
		assertFalse(catched);
	}
	
	@Test
	public void testMockBuscar() throws InsufficientPermissionsException, UnexistingTokenException{
		Servidor s1 = new ServidorSimple("serv","42");
		List<Contenido> listCancion = new ArrayList<>();
		listCancion.add(cancionMock);
		List<Contenido> listPubli = new ArrayList<>();
		List<Contenido> listEmisora = new ArrayList<>();
		listEmisora.add(emisoraMock);
		when(anuncioMock.buscar(anyString())).thenReturn(listPubli);
		when(cancionMock.buscar(anyString())).thenReturn(listCancion);
		when(emisoraMock.buscar(anyString())).thenReturn(listEmisora);
		when(emisoraMock.obtenerTitulo()).thenReturn("Emisora");
		when(anuncioMock.obtenerTitulo()).thenReturn("anuncio");
		when(cancionMock.obtenerTitulo()).thenReturn("Cancion");

		for  (int i = 0;i<5;i++){
			s1.agregar(cancionMock, "42");
		}
		s1.agregar(anuncioMock, "42");
		String token = s1.alta();
		String subChain = "Cancion";
		List<Contenido> resultado = new ArrayList<Contenido>();
		resultado = s1.buscar(subChain, token);
		assertEquals(resultado.size(),5);
		resultado.clear();
		resultado = s1.buscar(subChain, "r");
		assertEquals(resultado.size(),7);
	}
	
	@Test
	public void testMockBuscarReplicado(){
		
	}

}
