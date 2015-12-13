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
import es.udc.vvsTesting.contenido.Contenido;
import es.udc.vvsTesting.servidor.Servidor;
import es.udc.vvsTesting.servidor.ServidorConRespaldo;
import es.udc.vvsTesting.servidor.ServidorSimple;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.InvalidSongsDurationException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

import com.google.code.jetm.reporting.BindingMeasurementRenderer;
import com.google.code.jetm.reporting.xml.XmlAggregateBinder;

public class ServidorConRespaldoPerfTest {

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


	@Test
	public final void buscarPerformanceTest() throws InvalidSongsDurationException, InsufficientPermissionsException, UnexistingTokenException  {
		List<ServidorConRespaldo> testElements = 
				new ArrayList<ServidorConRespaldo>();
		List<String> tokenValido2 = new ArrayList<String>();
		for (int i = 0; i < itNumber; i++) {
			String nombre ="serv";
			String tokenValido = "45551";

			Servidor servidorRespaldo = new ServidorSimple(nombre,tokenValido);
			ServidorConRespaldo s1 = new ServidorConRespaldo(
					nombre, tokenValido, servidorRespaldo);

			String titulo = "a";
			String titulo1 = "a";
			String titulo2 = "b";

			Integer duracion = 10;
			Integer duracion1 = 9;
			Integer duracion2 = 8;

			Contenido cancion = new Cancion(titulo, duracion);
			Contenido cancion1 = new Cancion(titulo1, duracion1);
			Contenido cancion2 = new Cancion(titulo2, duracion2);
			tokenValido2.add(s1.alta());
			servidorRespaldo.agregar(cancion, tokenValido);
			servidorRespaldo.agregar(cancion1, tokenValido);
			servidorRespaldo.agregar(cancion2, tokenValido);
			servidorRespaldo.agregar(cancion2, tokenValido);

			testElements.add(s1);
		}

		EtmPoint point = etmMonitor
				.createPoint("ServidorConRespaldo:buscar");
		int i = 0;
		for (ServidorConRespaldo s : testElements) {
			s.buscar("a", tokenValido2.get(i));
			i++;
		}

		point.collect();
	}
}
