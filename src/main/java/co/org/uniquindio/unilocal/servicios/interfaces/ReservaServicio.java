package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.reserva.*;

import java.util.List;

public interface ReservaServicio {


    void RegistrarReserva(RegistroReservaDTO registroReservaDTO) throws Exception;

    void ActualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception;

    DetalleReservaDTO ObtenerReserva(String idNegocio, String idCliente) throws Exception;

    void EliminarReserva(String idNegocio, String idCliente) throws Exception;

    List<DetalleReservaDTO> ListarReservas(String idNegocio);


}
