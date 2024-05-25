package co.org.uniquindio.unilocal.dto.negocio;

import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ItemNegocioDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String nombre,
        @NotBlank String imagenDestacada,
        @NotBlank CategoriaNegocio categoriaNegocio,
        @NotBlank Ubicacion ubicacion,
        @NotNull @PositiveOrZero float calificacionPromedio,
        @NotBlank EstadoNegocio estadoNegocio
) {
}
