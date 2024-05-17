package co.org.uniquindio.unilocal.dto.Moderador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record RevisionesModeradorDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String descripcion,
        @NotBlank String codigoModerador,
        @NotBlank String respuesta


) {
}
