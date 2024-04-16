package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ModeradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModeradorServicioImpl implements ModeradorServicio {

    // RechazarLugar idNegocio, idModerador, motivo, estado.Activo ---
    // AutorizarzarLugar idNegocio, idModerador, motivo, estado.Activo ---
    // AutorizarzarLugar
    // VerHistorial dado el id del negocio hacer un getHistorialRevisiones()
    // EnviarCorreoRechazo
}
