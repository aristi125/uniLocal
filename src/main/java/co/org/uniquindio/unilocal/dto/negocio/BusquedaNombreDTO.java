package co.org.uniquindio.unilocal.dto.negocio;

import jakarta.validation.constraints.NotBlank;

public record BusquedaNombreDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String nombre
) {
}
