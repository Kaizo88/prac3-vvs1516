package es.udc.vvsTesting.performance;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import etm.core.configuration.BasicEtmConfigurator;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
import es.udc.vvsTesting.contenido.Cancion;
import es.udc.vvsTesting.servidor.Servidor;
import es.udc.vvsTesting.servidor.ServidorSimple;
import es.udc.vvsTesting.utils.ContentNotFoundException;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.InvalidSongsDurationException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

import com.google.code.jetm.reporting.BindingMeasurementRenderer;
import com.google.code.jetm.reporting.xml.XmlAggregateBinder;

public class ServidorSimplePerfTest {

	private static EtmMonitor etmMonitor;

    /**
     * Configure JETM
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        BasicEtmConfigurator.configure();

        etmMonitor = EtmManager.getEtmMonitor();
        etmMonitor.start();
    }

    /**
     * Write out the results of all of the test runs. This writes out 
     * the collected point data to an XML file located in target/jetm
     * beneath the working directory.
     * 
     * @throws Exception
     *             If any errors occur during the write-out.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        if (etmMonitor != null) {
        	etmMonitor.stop();

            final File timingDirectory = new File("target/jetm");
            FileUtils.forceMkdir(timingDirectory);

            final File timingFile = new File(timingDirectory, "Timing" + ".xml");
            final FileWriter writer = new FileWriter(timingFile);
            try {
            	etmMonitor.render(new BindingMeasurementRenderer(new XmlAggregateBinder(), writer));
            } finally {
                writer.close();
            }
        }
    }

	// Performance Variables

	/** Number of iteratios to check performance. */
	private final Integer itNumber = 10000;


	/**
	 * Obtener nombre test.
	 */
	@Test
	public final void obtenerNombrePerformanceTest() {
		List<Servidor> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombre = "serv";
			Servidor s = new ServidorSimple(nombre, "");
			testElements.add(s);
		}

		EtmPoint point = etmMonitor.createPoint("ServidorSimple:obtenerTitulo");

		for (Servidor s : testElements) {
			s.obtenerNombre();
		}

		point.collect();
	}
	
	@Test
	public final void altaPerformanceTest(){
		Servidor s = new ServidorSimple("1","1");
		
		EtmPoint point = etmMonitor.createPoint("ServidorSimple:alta");
		for (int i = 0; i < itNumber; i++){
			s.alta();
		}
		point.collect();
	}

	/**
	 * Agregar test.
	 * @throws InsufficientPermissionsException 
	 * @throws InvalidSongsDurationException 
	 */
	@Test
	public final void agregarPerformanceTest() throws InsufficientPermissionsException, InvalidSongsDurationException {
		List<Cancion> testElements = new ArrayList<>();
		Servidor serv = new ServidorSimple("serv","1");

		for (int i = 0; i < itNumber; i++) {

			String nombreCancion = "can";
			Integer duracionCancion = 5;
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);

			testElements.add(cancion);
		}

		EtmPoint point = etmMonitor.createPoint("ServidorSimple:agregar");

		for (Cancion c : testElements) {
			serv.agregar(c, "1");
		}

		point.collect();
	}

	/**
	 * Eliminar test.
	 * @throws InvalidSongsDurationException 
	 * @throws InsufficientPermissionsException 
	 * @throws UnexistingTokenException 
	 * @throws ContentNotFoundException 
	 */
	@Test
	public final void eliminarPerformanceTest() throws InvalidSongsDurationException, InsufficientPermissionsException, UnexistingTokenException, ContentNotFoundException {
		List<Servidor> testElements = new ArrayList<>();
		final String passwd = "1";

		String titulo = "can";
		Integer duracion = 2;
		Cancion cancion = new Cancion(titulo, duracion);
		for (int i = 0; i < itNumber; i++) {
			String nombre = "serv";
			Servidor s = new ServidorSimple(nombre, passwd);
			s.agregar(cancion, passwd);
			testElements.add(s);
		}

		EtmPoint point = etmMonitor.createPoint("ServidorSimple:eliminar");

		for (Servidor s : testElements) {
			s.eliminar(cancion, "1");
		}

		point.collect();
	}


	@Test
	public void bajaPerformanceTest() throws UnexistingTokenException{
		List<String> testElements = 
				new ArrayList<>();
		String nombre = "serv";
		String passwd = "1";
		Servidor servidor = new ServidorSimple(nombre, passwd);
		for (int i = 0; i < itNumber; i++) {
			
			testElements.add(servidor.alta());
		}

		EtmPoint point = etmMonitor
				.createPoint("ServidorSimple:baja");

		for (String token : testElements) {
			servidor.baja(token);
		}

		point.collect();
	
	}
}
