package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.dto.comentario.QuienHizoComentarioDTO;

import java.util.List;

public interface ComentarioServicio {
    void crearComentario();

    void responderComentario();

    List<ItemListaComentariosDTO> listarComentariosNegocio(QuienHizoComentarioDTO hizoComentarioDTO) throws Exception;

    void calcularPromedioCalificaciones();


}
