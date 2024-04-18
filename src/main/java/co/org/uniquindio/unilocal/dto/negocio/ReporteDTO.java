package co.org.uniquindio.unilocal.dto.negocio;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ReporteDTO(

        @NotBlank String codigoNegocio,
        @NotBlank String nombreNegocio,
        @NotBlank LocalDateTime fecha,
        @NotBlank int numReservas
) {
}
