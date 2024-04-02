package co.org.uniquindio.unilocal.modelo.Servicios.interfaces;

import co.org.uniquindio.unilocal.dto.CuentaDTO.SesionDTO;
import co.org.uniquindio.unilocal.dto.CuentaDTO.CambioPasswordDTO;

public interface CuentaServicio {
    void iniciarSesion(SesionDTO sesionDTO)throws Exception;
    void eliminarCuenta(String idCuenta)throws Exception;
    void enviarLinkRecuperacion(String email)throws Exception;
    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;
}
