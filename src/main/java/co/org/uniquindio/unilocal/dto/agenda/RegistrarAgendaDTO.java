package co.org.uniquindio.unilocal.dto.agenda;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public record RegistrarAgendaDTO(
        @NotBlank String codigoNegocio,
        @NotBlank @Length(max = 20) String tematica,
        @NotBlank @Length(max = 100) String descripcion
) {
}
