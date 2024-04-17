package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.reserva.*;

import java.util.List;

public interface ReservaServicio {


    void RegistrarReserva(RegistroReservaDTO registroReservaDTO) throws Exception;

    void ActualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception;

    DetalleReservaDTO ObtenerReserva(String idReserva) throws Exception;

    void EliminarReserva(String idReserva) throws Exception;

    List<DetalleReservaDTO> ListarReservas();


}
