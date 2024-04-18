package co.org.uniquindio.unilocal.dto.agenda;

import jakarta.validation.constraints.NotBlank;

public record DetalleAgendaDTO (
        @NotBlank String tematica,
        @NotBlank String descripcion
) {
}
