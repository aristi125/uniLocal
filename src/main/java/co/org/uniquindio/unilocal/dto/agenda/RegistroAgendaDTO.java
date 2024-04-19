package co.org.uniquindio.unilocal.dto.agenda;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public record RegistroAgendaDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String tematica,
        @NotBlank String descripcion
) {
}
