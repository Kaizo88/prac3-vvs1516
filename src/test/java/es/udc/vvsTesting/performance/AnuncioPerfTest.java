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

import com.google.code.jetm.reporting.BindingMeasurementRenderer;
import com.google.code.jetm.reporting.xml.XmlAggregateBinder;

public class AnuncioPerfTest {


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
		@Test
		public final void obtenerTituloPerformanceTest() {
			List<Anuncio> testElements = new ArrayList<Anuncio>();
			for (int i = 0; i < itNumber; i++) {
				testElements.add(new Anuncio());
			}

			EtmPoint point = etmMonitor
					.createPoint("AnuncioPerfTest:obtenerTitulo");

			for (Anuncio a : testElements) {
				a.obtenerTitulo();
			}

			point.collect();
		}

		/**
		 * Obtener duracion test.
		 */
		@Test
		public final void obtenerDuracionPerformanceTest() {
			List<Anuncio> testElements = new ArrayList<Anuncio>();
			for (int i = 0; i < itNumber; i++) {
				testElements.add(new Anuncio());
			}

			EtmPoint point = etmMonitor
					.createPoint("AnuncioPerfTest:obtenerDuracion");

			for (Anuncio a : testElements) {
				a.obtenerDuracion();
			}

			point.collect();
		}

		/**
		 * Obtener lista reproduccion test.
		 */
		@Test
		public final void obtenerListaReproduccionPerformanceTest() {
			List<Anuncio> testElements = new ArrayList<Anuncio>();
			for (int i = 0; i < itNumber; i++) {
				Anuncio anuncio = new Anuncio();
				testElements.add(anuncio);
			}

			EtmPoint point = etmMonitor
					.createPoint("AnuncioPerfTest:obtenerListaReproduccion");

			for (Anuncio a : testElements) {
				a.obtenerListaReproduccion();
			}

			point.collect();
		}

		/**
		 * Buscar test.
		 */
		@Test
		public final void buscarPerformanceTest() {
			List<Anuncio> testElements = new ArrayList<Anuncio>();
			for (int i = 0; i < itNumber; i++) {
				Anuncio anuncio = new Anuncio();
				testElements.add(anuncio);
			}

			EtmPoint point = etmMonitor.createPoint("AnuncioPerfTest:buscar");

			for (Anuncio a : testElements) {
				a.buscar("PUBLICIDAD");
			}

			point.collect();
		}
	
}
