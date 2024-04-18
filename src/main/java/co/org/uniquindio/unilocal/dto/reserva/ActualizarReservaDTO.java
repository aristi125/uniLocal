package co.org.uniquindio.unilocal.dto.reserva;

import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ActualizarReservaDTO (

        @NotBlank LocalDate fecha,
        @NotBlank LocalDateTime hora,
        @NotBlank int cantidadPersonas,
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio

) {
}
