package co.org.uniquindio.unilocal.dto.reserva;

import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record DetalleReservaDTO (
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio,
        @NotNull int cantidadPersonas
        ) {

}
