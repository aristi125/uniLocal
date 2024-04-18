package co.org.uniquindio.unilocal.dto.reserva;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;

public record ActualizarReservaDTO (

        @NotBlank LocalDate fecha,
        @NotBlank LocalTime hora,
        @NotBlank int cantidadPersonas,
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio

) {
}
