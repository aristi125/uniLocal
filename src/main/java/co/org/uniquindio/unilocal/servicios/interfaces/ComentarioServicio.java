package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.comentario.ComentarioDTO;
import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.dto.comentario.QuienHizoComentarioDTO;

import java.util.List;

public interface ComentarioServicio {
    void crearComentario(ComentarioDTO comentario);

    void responderComentario(ComentarioDTO comentario);

    List<ItemListaComentariosDTO> listarComentariosNegocio(QuienHizoComentarioDTO hizoComentarioDTO) throws Exception;

    void calcularPromedioCalificaciones();
}
