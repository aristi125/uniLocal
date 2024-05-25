package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.cuenta.SesionDTO;
import co.org.uniquindio.unilocal.dto.TokenDTO;

public interface AutentificacionServicio {

    TokenDTO iniciarSesion(SesionDTO sesionDTO) throws Exception;

}
