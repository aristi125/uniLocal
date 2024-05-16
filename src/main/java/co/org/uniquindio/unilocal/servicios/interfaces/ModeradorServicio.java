package co.org.uniquindio.unilocal.servicios.interfaces;


import co.org.uniquindio.unilocal.dto.Moderador.RevisionesModeradorDTO;
import co.org.uniquindio.unilocal.dto.comentario.RevisarComentariosDTO;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.negocio.ItemNegociosRevisionDTO;
import co.org.uniquindio.unilocal.modelo.documentos.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;

import java.util.List;

public interface ModeradorServicio  {

    List<HistorialRevision> obtenerHistorialRevisiones(String idNegocio) throws Exception;
    List<RevisarComentariosDTO> revisarComentarios(String codigo)throws Exception;
    List<ItemNegociosRevisionDTO>listarRevisiones(EstadoNegocio estadoNegocio)throws Exception;
    void bloquearUsuario(String codigo)throws Exception;
    void rechazarNegocio(RevisionesModeradorDTO revisionesModeradorDTO)throws Exception;
    void aprobarNegocio(RevisionesModeradorDTO revisionesModeradorDTO)throws Exception;
    void enviarLinkRecuperacion(String email)throws Exception;
    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;

}
