package es.udc.vvsTesting.quickcheck.servidor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.PrimitiveGenerators;

import org.junit.Test;

import es.udc.vvsTesting.servidor.Servidor;
import es.udc.vvsTesting.servidor.ServidorSimple;

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
}
