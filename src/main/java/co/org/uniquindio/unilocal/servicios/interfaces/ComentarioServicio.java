package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.comentario.*;

import java.util.List;

public interface ComentarioServicio {
    void crearComentario(RegistroComentarioDTO comentario) throws Exception;

    void responderComentario(RespuestaComentarioDTO comentario) throws Exception;

    List<ItemListaComentariosDTO> listarComentariosNegocio(String codigoNegocio) throws Exception;

    int calcularPromedioCalificaciones(String codigoNegocio) throws Exception;
    
}
