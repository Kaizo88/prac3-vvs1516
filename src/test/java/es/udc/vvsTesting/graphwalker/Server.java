package es.udc.vvsTesting.graphwalker;

import org.graphwalker.java.annotation.Edge;
import org.graphwalker.java.annotation.Model;
import org.graphwalker.java.annotation.Vertex;

@Model(file = "es/udc/vvsTesting/Server.graphml")
public interface Server {

    @Vertex()
    void Ready();

    @Vertex()
    void Servidor_con_contenido();

    @Vertex()
    void Servidor_con_tokenYcontendio();

    @Edge()
    void agregar_contenido();

    @Edge()
    void baja_token();

    @Edge()
    void alta_token();

    @Vertex()
    void Servidor_con_token();

    @Edge()
    void iniciar_servidor();

    @Edge()
    void eliminar_contenido();
}
