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
    public void RegistrarReserva(RegistroReservaDTO registroReservaDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findById(registroReservaDTO.codigoCliente());
        if (clienteOptional.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        Optional<Negocio> negocioOptional = negocioRepo.findById(registroReservaDTO.codigoNegocio());
        if (negocioOptional.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = negocioOptional.get();

        List<Reserva> listaReservas =  negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getFecha().equals(registroReservaDTO.fecha()) && reserva.getHora().equals(registroReservaDTO.hora())) {
                throw new Exception("Ya existe una reserva para esa fecha y hora");
            }
        }

        Reserva reserva = new Reserva();
        reserva.setFecha(registroReservaDTO.fecha());
        reserva.setHora(registroReservaDTO.hora());
        reserva.setCantidadPersonas(registroReservaDTO.cantidadPersonas());
        reserva.setCodigoCliente(registroReservaDTO.codigoCliente());
        reserva.setCodigoNegocio(registroReservaDTO.codigoNegocio());

        negocio.getListaReservas().add(reserva);
        negocioRepo.save(negocio);
    }

    @Override
    public void ActualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception {

        Optional<Cliente> clienteOptional = clienteRepo.findById(actualizarReservaDTO.codigoCliente());
        if (clienteOptional.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        Optional<Negocio> negocioOptional = negocioRepo.findById(actualizarReservaDTO.codigoNegocio());
        if (negocioOptional.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = negocioOptional.get();

        List<Reserva> listaReservas =  negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getFecha().equals(actualizarReservaDTO.fecha()) && reserva.getHora().equals(actualizarReservaDTO.hora())) {
                throw new Exception("Ya existe una reserva para esa fecha y hora");
            }
        }

        List<Reserva> listaReservasNegocio = negocio.getListaReservas();
        for (Reserva reserva : listaReservasNegocio) {
            if (reserva.getCodigoCliente().equals(actualizarReservaDTO.codigoCliente()) ){
                reserva.setFecha(actualizarReservaDTO.fecha());
                reserva.setHora(actualizarReservaDTO.hora());
                reserva.setCantidadPersonas(actualizarReservaDTO.cantidadPersonas());
                reserva.setCodigoCliente(actualizarReservaDTO.codigoCliente());
                reserva.setCodigoNegocio(actualizarReservaDTO.codigoNegocio());
                negocioRepo.save(negocio);
                return;
            }
        }


    }

    @Override
    public DetalleReservaDTO ObtenerReserva(String idNegocio, String idCliente) throws Exception {

        Optional<Negocio> negocioOptional = negocioRepo.findById(idNegocio);
        if (negocioOptional.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Optional<Cliente> clienteOptional = clienteRepo.findById(idCliente);
        if (clienteOptional.isEmpty()) {
            throw new Exception("El cliente no existe");
        }


        Negocio negocio = negocioOptional.get();

        List<Reserva> listaReservas = negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getCodigoCliente().equals(idCliente)) {
                return new DetalleReservaDTO(reserva.getFecha(), reserva.getHora(), reserva.getCantidadPersonas(), reserva.getCodigoCliente(), reserva.getCodigoNegocio()) ;

            }

        }
        throw new Exception("La reserva no existe");

    }

    @Override
    public void EliminarReserva(String idNegocio, String idCliente) throws Exception {

        Optional<Negocio> negocioOptional = negocioRepo.findById(idNegocio);
        if (negocioOptional.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Optional<Cliente> clienteOptional = clienteRepo.findById(idCliente);
        if (clienteOptional.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        Negocio negocio = negocioOptional.get();

        List<Reserva> listaReservas = negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getCodigoCliente().equals(idCliente)) {
                negocio.getListaReservas().remove(reserva);
                negocioRepo.save(negocio);
                return;
            }
        }
        throw new Exception("La reserva no existe");

    }

    @Override
    public List<DetalleReservaDTO> ListarReservas(String idNegocio) {
        List<DetalleReservaDTO> respuesta = new ArrayList<>();
        Optional<Negocio> negocioOptional = negocioRepo.findById(idNegocio);
        if (negocioOptional.isEmpty()) {
            throw new RuntimeException("El negocio no existe");
        }

        Negocio negocio = negocioOptional.get();

        List<Reserva> listaReservas = negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            respuesta.add(new DetalleReservaDTO(reserva.getFecha(), reserva.getHora(), reserva.getCantidadPersonas(), reserva.getCodigoCliente(), reserva.getCodigoNegocio()));
        }
        return respuesta;


    }
}
