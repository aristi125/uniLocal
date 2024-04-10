package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.reserva.ActualizarReservaDTO;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.dto.reserva.RegistroReservaDTO;
import co.org.uniquindio.unilocal.servicios.interfaces.ReservaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservaServicioImpl implements ReservaServicio {
    @Override
    public String RegistrarReserva(RegistroReservaDTO registroReservaDTO) throws Exception {
        return null;
    }

    @Override
    public void ActualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception {

    }

    @Override
    public DetalleReservaDTO ObtenerReserva(String idReserva) throws Exception {
        return null;
    }

    @Override
    public void EliminarReserva(String idReserva) throws Exception {

    }

    @Override
    public List<DetalleReservaDTO> ListarReservas() {
        return null;
    }
}
