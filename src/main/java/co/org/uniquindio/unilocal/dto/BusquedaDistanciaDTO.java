package co.org.uniquindio.unilocal.dto;

import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import jakarta.validation.constraints.NotNull;

public record BusquedaDistanciaDTO(
        @NotNull Ubicacion ubicacion,
        @NotNull double distancia
        ) {
}
