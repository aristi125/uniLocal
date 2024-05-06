package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//NOTA: Esta clase es obtener los favoritos de un cliente
public record FavoritoDTO(
        @NotBlank String idNegocio,
        @NotBlank String idCliente,
        @NotBlank String urlFoto,
        @NotNull Ubicacion lugar
) {
}
