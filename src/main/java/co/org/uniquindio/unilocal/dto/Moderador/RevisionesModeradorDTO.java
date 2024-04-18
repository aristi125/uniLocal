package co.org.uniquindio.unilocal.dto.Moderador;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record RevisionesModeradorDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String descripcion,
        @NotBlank String codigoModerador,
        @NotBlank String respuesta,
        @NotBlank LocalDate fecha


) {
}
