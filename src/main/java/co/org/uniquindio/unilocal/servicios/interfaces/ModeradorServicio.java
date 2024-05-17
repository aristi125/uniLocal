package co.org.uniquindio.unilocal.servicios.interfaces;


import co.org.uniquindio.unilocal.dto.Moderador.DetalleModeradorDTO;
import co.org.uniquindio.unilocal.dto.Moderador.RevisionesModeradorDTO;
import co.org.uniquindio.unilocal.dto.cliente.DetalleClienteDTO;
import co.org.uniquindio.unilocal.dto.comentario.RevisarComentariosDTO;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.cuenta.LinkRecuperacionDTO;
import co.org.uniquindio.unilocal.dto.negocio.ItemNegociosRevisionDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.documentos.Moderador;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;

import java.util.List;

public interface ModeradorServicio  {

    DetalleModeradorDTO obtenerModerador(String idCuenta) throws Exception;
    void enviarLinkRecuperacionModerador(LinkRecuperacionDTO linkRecuperacionDTO)throws Exception;
    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;
    Moderador buscarModerador(String idCuenta) throws Exception;

}
