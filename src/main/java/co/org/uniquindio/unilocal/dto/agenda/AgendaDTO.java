package co.org.uniquindio.unilocal.dto.agenda;

import jakarta.validation.constraints.NotBlank;


public record AgendaDTO(
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio
) {
}
