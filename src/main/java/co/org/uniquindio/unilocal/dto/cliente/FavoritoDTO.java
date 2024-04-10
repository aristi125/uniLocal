package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;

public record FavoritoDTO(
        String idNegocio,
        String urlFoto,
        String nombre,
        Ubicacion Lugar
) {
}