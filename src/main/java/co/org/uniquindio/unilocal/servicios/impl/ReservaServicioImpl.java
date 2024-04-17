package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.reserva.ActualizarReservaDTO;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.dto.reserva.RegistroReservaDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.entidades.Reserva;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ReservaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservaServicioImpl implements ReservaServicio {

    private final ClienteRepo clienteRepo;
    private final NegocioRepo negocioRepo;

    @Override
    public String RegistrarReserva(RegistroReservaDTO registroReservaDTO) throws Exception {
        Optional<Cliente> cliente = clienteRepo.findById(registroReservaDTO.codigoCliente());
        if (cliente.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        Optional<Negocio> negocio = negocioRepo.findById(registroReservaDTO.codigoNegocio());
        if (negocio.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Reserva reserva = new Reserva();
        reserva.setFecha(registroReservaDTO.fecha());
        reserva.setHora(registroReservaDTO.hora());
        reserva.setCantidadPersonas(registroReservaDTO.cantidadPersonas());
        reserva.setCodigoCliente(registroReservaDTO.codigoCliente());
        reserva.setCodigoNegocio(registroReservaDTO.codigoNegocio());

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
