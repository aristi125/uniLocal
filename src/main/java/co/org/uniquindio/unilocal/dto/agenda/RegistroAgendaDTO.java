package co.org.uniquindio.unilocal.dto.agenda;

import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoAgenda;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public record RegistroAgendaDTO(
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio,
        @NotBlank String tematica,
        @NotBlank String descripcion,
        @NotNull EstadoAgenda estadoAgenda
) {
}
