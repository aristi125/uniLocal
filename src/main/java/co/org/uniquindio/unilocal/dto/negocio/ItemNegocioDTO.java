package co.org.uniquindio.unilocal.dto.negocio;

import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import jakarta.validation.constraints.NotBlank;

public record ItemNegocioDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String nombre,
        @NotBlank String imagenDestacada,
        @NotBlank CategoriaNegocio tipoNegocio,
        @NotBlank Ubicacion ubicacion,
        @NotBlank Long calificacionPromedio,
        @NotBlank EstadoNegocio estadoNegocio
) {
}
