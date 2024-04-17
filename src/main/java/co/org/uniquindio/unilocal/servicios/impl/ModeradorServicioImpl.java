package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
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
    private final NegocioRepo negocioRepo;

    // RechazarLugar idNegocio, idModerador, motivo, estado.PENDIENTE ---
    // AutorizarzarLugar idNegocio, idModerador, motivo, estado.PENDIENTE ---
    // VerHistorial dado el id del negocio hacer un getHistorialRevisiones()
    /*public List<Optional> verHistorial (String codigoNegocio) {
        Negocio negocio = new Negocio();
        Optional<Negocio> negocio = negocioRepo.findAllByCodigo(codigoNegocio);
    }*/
    // Ver lugares pendientes de autorizar o rechazar

    // EnviarCorreoRechazo
}
