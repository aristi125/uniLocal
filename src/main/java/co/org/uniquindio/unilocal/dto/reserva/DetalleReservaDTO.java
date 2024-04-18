package co.org.uniquindio.unilocal.dto.reserva;

import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record DetalleReservaDTO (
        LocalDate fecha,
        LocalDateTime hora,
        int cantidadPersonas,
        String codigoCliente,
        String codigoNegocio
) {

}
