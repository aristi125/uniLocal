package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.reserva.*;

import java.util.List;

public interface ReservaServicio {


    void registrarReserva(RegistroReservaDTO registroReservaDTO) throws Exception;

    void actualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception;

    DetalleReservaDTO obtenerReserva(String idNegocio, String idCliente) throws Exception;

    void eliminarReserva(String idNegocio, String idCliente) throws Exception;

    List<DetalleReservaDTO> listarReservas(String idNegocio);


}
