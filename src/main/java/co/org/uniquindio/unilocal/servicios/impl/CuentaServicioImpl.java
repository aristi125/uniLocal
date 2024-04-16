package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.Cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.Cuenta.SesionDTO;
import co.org.uniquindio.unilocal.servicios.interfaces.CuentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CuentaServicioImpl implements CuentaServicio {
    @Override
    public void iniciarSesion(SesionDTO sesionDTO) throws Exception {

    }

    //esto si va aqui? o en clienteServicioImpl?
    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {

    }
}
