package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.reserva.ActualizarReservaDTO;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.dto.reserva.RegistroReservaDTO;
import co.org.uniquindio.unilocal.modelo.documentos.*;
import co.org.uniquindio.unilocal.modelo.entidades.Reserva;
import co.org.uniquindio.unilocal.repositorios.*;
import co.org.uniquindio.unilocal.servicios.interfaces.ReservaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
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
        Optional<Cliente> optionalCliente = clienteRepo.findById(registroReservaDTO.codigoCliente());
        if (optionalCliente.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        Optional<Negocio> optionalNegocio = negocioRepo.findById(registroReservaDTO.codigoNegocio());
        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = optionalNegocio.get();
        for (Reserva reserva : negocio.getListaReservas()) {
            if (reserva.getFecha().equals(registroReservaDTO.fecha()) && reserva.getHora() == registroReservaDTO.hora()) {
                throw new Exception("Ya existe una reserva en esa fecha y hora");
            }
        }

        Reserva reserva = new Reserva();
        reserva.setFecha(registroReservaDTO.fecha());
        reserva.setHora(registroReservaDTO.hora());
        reserva.setCantidadPersonas(registroReservaDTO.cantidadPersonas());
        reserva.setCodigoCliente(registroReservaDTO.codigoCliente());
        reserva.setCodigoNegocio(registroReservaDTO.codigoNegocio());

        negocio.getListaReservas().add(reserva);

        return reserva.getCodigo();
    }

    @Override
    public void ActualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception {

        Optional<Cliente> optionalCliente = clienteRepo.findById(actualizarReservaDTO.codigoCliente());
        if (optionalCliente.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        Optional<Negocio> optionalNegocio = negocioRepo.findById(actualizarReservaDTO.codigoNegocio());
        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no existe");
        }


        Negocio negocio = optionalNegocio.get();

        for (Reserva reserva : negocio.getListaReservas()) {
            if (reserva.getFecha().equals(actualizarReservaDTO.fecha()) && reserva.getHora() == actualizarReservaDTO.hora()) {
                throw new Exception("Ya existe una reserva en esa fecha y hora");
            }
        }

        for (Reserva reserva : negocio.getListaReservas()) {
            if (reserva.getCodigo().equals(actualizarReservaDTO.codigoReserva())) {
                reserva.setFecha(actualizarReservaDTO.fecha());
                reserva.setHora(actualizarReservaDTO.hora());
                reserva.setCantidadPersonas(actualizarReservaDTO.cantidadPersonas());
                reserva.setCodigoCliente(actualizarReservaDTO.codigoCliente());
                reserva.setCodigoNegocio(actualizarReservaDTO.codigoNegocio());
                return;
            }
            throw new Exception("La reserva no existe");
        }


    }

    @Override
    public DetalleReservaDTO ObtenerReserva(String idNegocio, String idReserva) throws Exception {

        Optional<Negocio> optionalNegocio = negocioRepo.findById(idNegocio);
        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = optionalNegocio.get();

        for (Reserva reserva : negocio.getListaReservas()) {
            if (reserva.getCodigo().equals(idReserva)) {
                return new DetalleReservaDTO(
                        reserva.getCodigo(),
                        reserva.getFecha(),
                        reserva.getHora(),
                        reserva.getCantidadPersonas(),
                        reserva.getCodigoCliente(),
                        reserva.getCodigoNegocio()
                );
            }
        }

        throw new Exception("La reserva no existe");


    }

    @Override
    public void EliminarReserva(String idNegocio, String idReserva) throws Exception {

        Optional<Negocio> optionalNegocio = negocioRepo.findById(idNegocio);
        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = optionalNegocio.get();

        for (Reserva reserva : negocio.getListaReservas()) {
            if (reserva.getCodigo().equals(idReserva)) {
                negocio.getListaReservas().remove(reserva);
                return;
            }
        }

        throw new Exception("La reserva no existe");
    }

    @Override
    public List<DetalleReservaDTO> ListarReservas(String idNegocio) throws Exception {

        Optional<Negocio> optionalNegocio = negocioRepo.findById(idNegocio);
        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = optionalNegocio.get();

        List<DetalleReservaDTO> items = new ArrayList<>();
        for (Reserva reserva : negocio.getListaReservas()) {
            items.add(new DetalleReservaDTO(
                    reserva.getCodigo(),
                    reserva.getFecha(),
                    reserva.getHora(),
                    reserva.getCantidadPersonas(),
                    reserva.getCodigoCliente(),
                    reserva.getCodigoNegocio()
            ));
        }
        return items;

    }
}
