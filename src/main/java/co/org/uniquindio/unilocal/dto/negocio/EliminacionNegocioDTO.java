package co.org.uniquindio.unilocal.dto.negocio;

import jakarta.validation.constraints.NotBlank;

public record EliminacionNegocioDTO(
        @NotBlank String idNegocio,
        @NotBlank String idCliente
) {
}
