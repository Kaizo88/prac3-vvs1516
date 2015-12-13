package es.udc.vvsTesting.graphwalker;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;

import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.test.TestBuilder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import es.udc.vvsTesting.contenido.Cancion;
import es.udc.vvsTesting.contenido.Contenido;
import es.udc.vvsTesting.servidor.ServidorImp;
import es.udc.vvsTesting.utils.ContentNotFoundException;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.InvalidSongsDurationException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

public class ServidorTest extends ExecutionContext implements Server {
    public final static Path MODEL_PATH = Paths.get("es/udc/vvsTesting/Server.graphml");

    private ServidorImp s;
	public void Ready() {
		assertTrue(s.getTokens().isEmpty());
		assertTrue(s.getContentList().isEmpty());

		System.out.println("Ready");
		
	}

	public void buscar_contenido() {
		// TODO Auto-generated method stub
		
	}

	public void Servidor_con_contenido() {
		assertTrue(s.getTokens().isEmpty());
		System.out.println("Servidor con contenido");
		
	}

	public void Servidor_con_tokenYcontendio() {
		assertFalse(s.getTokens().isEmpty());
		assertFalse(s.getContentList().isEmpty());


		System.out.println("Servidor con token y contenido");
		
	}

	public void agregar_contenido() {
		try {
			Contenido cancion1 = new Cancion("cancion1", 6);
			s.agregar(cancion1, "admin");
			
		} catch (InvalidSongsDurationException e) {
			e.printStackTrace();
		} catch (InsufficientPermissionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void baja_token() {
		try {
			s.baja(s.getTokens().entrySet().iterator().next().getKey());
		} catch (UnexistingTokenException e) {

		}
	}

	public void alta_token() {
		s.alta();
		
	}

	public void Servidor_con_token() {
		assertTrue(s.getContentList().isEmpty());
		
	}

	public void iniciar_servidor() {
	    s = new ServidorImp("server1","admin");
		
	}

	public void eliminar_contenido() {
		try {
			s.eliminar(s.getContentList().get(0), "admin");
		} catch (InsufficientPermissionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
    @Test
    public void runSmokeTest() {
        new TestBuilder()
            .setModel(MODEL_PATH)
            .setContext(new ServidorTest())
            .setPathGenerator(new AStarPath(new ReachedVertex("Servidor_con_tokenYcontendio"))) // xeramos un test que chegue a este estado
            .setStart("iniciar_servidor") // primeira chamada
            .execute();
    }
	
    @Test
    public void runFunctionalTest() {
        new TestBuilder()
            .setModel(MODEL_PATH)
            .setContext(new ServidorTest())
            .setPathGenerator(new RandomPath(new EdgeCoverage(100))) // xera tantos test como sexan necesarios para pasar alomenos unha vez por cada transición
            .setStart("iniciar_servidor") // primeira chamada
            .execute();
    }

  	
}
