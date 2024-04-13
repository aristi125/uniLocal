package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.comentario.DetalleComentarioDTO;
import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;

import java.util.List;

public interface ComentarioServicio {
    void crearComentario(DetalleComentarioDTO comentario);

    void responderComentario();

    List<ItemListaComentariosDTO> listarComentariosNegocio(DetalleComentarioDTO detalleComentarioDTO) throws Exception;

    void calcularPromedioCalificaciones();


}
