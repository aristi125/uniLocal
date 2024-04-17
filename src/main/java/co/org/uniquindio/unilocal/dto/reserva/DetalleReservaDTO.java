package co.org.uniquindio.unilocal.dto.reserva;

import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;

import java.time.LocalDateTime;

public record DetalleReservaDTO (
        String codigoReserva,
        LocalDateTime fecha,
        int hora,
        int cantidadPersonas,
        String cliente,
        String negocio
) {

}
