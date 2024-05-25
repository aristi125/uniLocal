package co.org.uniquindio.unilocal.dto.negocio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record ReporteDTO(

        @NotBlank String codigoPropietario,
        @NotBlank String codigoNegocio,
        @NotBlank String nombreNegocio,
        @NotNull @PositiveOrZero int numReservas
) {
}
