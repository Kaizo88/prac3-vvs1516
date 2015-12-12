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
import es.udc.vvsTesting.contenido.Anuncio;
import es.udc.vvsTesting.contenido.Cancion;
import es.udc.vvsTesting.contenido.Contenido;
import es.udc.vvsTesting.contenido.Emisora;
import es.udc.vvsTesting.utils.InvalidSongsDurationException;

import com.google.code.jetm.reporting.BindingMeasurementRenderer;
import com.google.code.jetm.reporting.xml.XmlAggregateBinder;

public class EmisoraPerfTest {

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
	 * Obtener titulo test.
	 */
	public final void obtenerTituloPerformanceTest() {
		List<Emisora> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombre = "Emi";
			Emisora emisora = new Emisora(nombre);

			testElements.add(emisora);
		}

		EtmPoint point = etmMonitor
				.createPoint("EmisoraPerformance:obtenerTitulo");

		for (Emisora e : testElements) {
			e.obtenerTitulo();
		}

		point.collect();
	}

	/**
	 * Obtener duracion test.
	 * @throws InvalidSongsDurationException 
	 */
	@Test
	public final void obtenerDuracionPerformanceTest() throws InvalidSongsDurationException {
		List<Emisora> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombreEmisora = "Emi";
			Emisora emisora = new Emisora(nombreEmisora);

			String nombreCancion = "Canc";
			Integer duracionCancion = 2;
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);

			emisora.agregar(cancion, emisora);

			testElements.add(emisora);
		}

		EtmPoint point = etmMonitor
				.createPoint("EmisoraPerformance:obtenerDuracion");

		for (Emisora e : testElements) {
			e.obtenerDuracion();
		}

		point.collect();
	}

	/**
	 * Obtener lista reproduccion test.
	 * @throws InvalidSongsDurationException 
	 */
	@Test
	public final void obtenerListaReproduccionPerformanceTest() throws InvalidSongsDurationException {
		List<Emisora> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombreEmisora = "Emi";
			Emisora emisora = new Emisora(nombreEmisora);

			String nombreCancion = "Cancion";
			Integer duracionCancion = 10;
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);

			emisora.agregar(cancion, emisora);
			testElements.add(emisora);
		}

		EtmPoint point = etmMonitor
				.createPoint("EmisoraPerformance:obtenerListaReproduccion");

		for (Emisora e : testElements) {
			e.obtenerListaReproduccion();
		}

		point.collect();
	}

	/**
	 * Buscar test.
	 */
	@Test
	public final void buscarPerformanceTest() {
		List<Emisora> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombreEmisora = "Emi";
			Emisora emisora = new Emisora(nombreEmisora);
			
			Anuncio anuncio = new Anuncio();
			emisora.agregar(anuncio, null);
			
			List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
			listaReproduccionIdeal.add(emisora);

			testElements.add(emisora);
		}

		EtmPoint point = etmMonitor.createPoint("EmisoraPerformance:buscar");

		for (Emisora e : testElements) {
			e.buscar("Emi");
		}

		point.collect();

	}
	/**
	 * Agregar test.
	 * @throws InvalidSongsDurationException 
	 */
	@Test
	public final void agregarPerformanceTest() throws InvalidSongsDurationException {
		List<Emisora> testElements = new ArrayList<>();
		List<Cancion> testSongs = new ArrayList<>();

		for (int i = 0; i < itNumber; i++) {
			String nombreEmisora = "Emi";
			Emisora emisora = new Emisora(nombreEmisora);

			String nombreCancion = "can";
			Integer duracionCancion = 2;
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);
			testSongs.add(cancion);
			testElements.add(emisora);
		}

		EtmPoint point = etmMonitor.createPoint("EmisoraPerformance:agregar");

		for (Emisora e : testElements) {
			e.agregar(testSongs.get(0),null);
		}

		point.collect();
	}

	/**
	 * Eliminar test.
	 * @throws InvalidSongsDurationException 
	 */
	@Test
	public final void eliminarPerformanceTest() throws InvalidSongsDurationException {
		List<Emisora> testElements = new ArrayList<>();

		String nombreCancion = "c";
		Integer duracionCancion = 10;
		Cancion cancion = new Cancion(nombreCancion, duracionCancion);
		for (int i = 0; i < itNumber; i++) {
			String nombreEmisora = "Emi";
			Emisora emisora = new Emisora(nombreEmisora);

			emisora.agregar(cancion, emisora);

			testElements.add(emisora);
		}

		EtmPoint point = etmMonitor.createPoint("EmisoraPerformance:eliminar");

		for (Emisora e : testElements) {
			e.eliminar(cancion);
		}

		point.collect();

	}


}
