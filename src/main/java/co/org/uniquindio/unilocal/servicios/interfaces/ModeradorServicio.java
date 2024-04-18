package co.org.uniquindio.unilocal.servicios.interfaces;


import co.org.uniquindio.unilocal.dto.Moderador.RevisionesModeradorDTO;
import co.org.uniquindio.unilocal.dto.comentario.DetalleComentarioDTO;
import co.org.uniquindio.unilocal.dto.negocio.ItemNegociosRevisionDTO;
import co.org.uniquindio.unilocal.modelo.entidades.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoRevision;

import java.util.List;

public interface ModeradorServicio  {
    List<HistorialRevision> getHistorialRevisiones(String idNegocio) throws Exception;

    List<DetalleComentarioDTO> revisarComentarios(String codigo)throws Exception;
    List<ItemNegociosRevisionDTO>listarRevisiones(EstadoRevision estadoRevision)throws Exception;
    void bloquearUsuario(String codigo)throws Exception;
    void revisarNegocio(RevisionesModeradorDTO revisionesModeradorDTO)throws Exception;
    void rechazarNegocio(RevisionesModeradorDTO revisionesModeradorDTO)throws Exception;
    void aceptarNegocio(RevisionesModeradorDTO revisionesModeradorDTO)throws Exception;
    //void actualizarPassword(CambiarPasswordModeradorDTO cambioPasswordDto)throws Exception;
}
