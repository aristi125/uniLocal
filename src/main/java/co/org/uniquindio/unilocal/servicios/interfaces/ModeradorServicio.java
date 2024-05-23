package co.org.uniquindio.unilocal.servicios.interfaces;


import co.org.uniquindio.unilocal.dto.Moderador.DetalleModeradorDTO;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.cuenta.LinkRecuperacionDTO;

import java.util.List;

public interface ModeradorServicio  {

    DetalleModeradorDTO obtenerModerador(String idCuenta) throws Exception;

    void enviarLinkRecuperacionModerador(LinkRecuperacionDTO linkRecuperacionDTO)throws Exception;

    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;

}
