package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.Cuenta.SesionDTO;
import co.org.uniquindio.unilocal.dto.TokenDTO;

public interface AutentificacionServicio {
    TokenDTO iniciarSesionCliente(SesionDTO sesionDTO) throws Exception;

    TokenDTO iniciarSesionModerador(SesionDTO sesionDTO) throws Exception;
}
