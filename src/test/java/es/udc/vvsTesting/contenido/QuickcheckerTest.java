package es.udc.vvsTesting.contenido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import net.java.quickcheck.ExtendibleGenerator;
import net.java.quickcheck.Generator;
import net.java.quickcheck.characteristic.Classification;
import net.java.quickcheck.generator.iterable.Iterables;
import static net.java.quickcheck.generator.PrimitiveGenerators.*;
import static net.java.quickcheck.generator.CombinedGenerators.lists;
import static net.java.quickcheck.generator.CombinedGeneratorsIterables.someLists;
import static org.junit.Assert.*;

public class QuickcheckerTest {

	//Comprobar que la duracion se actualiza correctamente al agregar contenido
	@Test
	public void QuickCheckerAdd() {

		int i=0;
		Classification c = new Classification();
		Emisora emisora = new Emisora("emisora");
		List<String> titulos = new ArrayList<String>();
		int duracionTotal = 0;
		
		for (Contenido contenido : Iterables.toIterable(new ContenidoGenerator())){
			duracionTotal = duracionTotal + contenido.obtenerDuracion();
			titulos.add(contenido.obtenerTitulo());
			emisora.agregar(contenido, null);
		}
		assertEquals(emisora.obtenerDuracion(),duracionTotal);
		
		
	}

	class ContenidoGenerator implements Generator<Contenido> {

		int START = 0;
		int END = 4;
		Random random = new Random();

		public Contenido next() {
			ExtendibleGenerator<Character, String> sGen = strings("abcdeABCDE",
					1, 5);
			Generator<Integer> sGen1 = integers(-100,0);
			if (random.nextInt(4) < 1) {
				return new Anuncio();
			} else {
				return new Cancion(sGen.next(), sGen1.next());
			}

		}

	}
}
