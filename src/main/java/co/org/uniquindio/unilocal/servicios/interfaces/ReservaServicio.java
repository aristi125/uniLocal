package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.reserva.ActualizarReservaDTO;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.dto.reserva.RegistroReservaDTO;

import java.util.List;

public interface ReservaServicio {


    String RegistrarReserva(RegistroReservaDTO registroReservaDTO) throws Exception;

    void ActualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception;

    DetalleReservaDTO ObtenerReserva(String idNegocio, String idReserva) throws Exception;

    void EliminarReserva(String idNegocio, String idReserva) throws Exception;

    List<DetalleReservaDTO> ListarReservas(String idNegocio) throws Exception;


}
