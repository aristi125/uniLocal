package co.org.uniquindio.unilocal.dto.reserva;


import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record RegistroReservaDTO (

        @NotBlank LocalDate fecha,
        @NotBlank LocalDateTime hora,
        @NotBlank int cantidadPersonas,
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio

) {
}
