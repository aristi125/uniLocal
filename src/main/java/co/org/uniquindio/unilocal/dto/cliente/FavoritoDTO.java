package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;

//NOTA: Esta clase es obtener los favoritos de un cliente
public record FavoritoDTO(
        String idNegocio,
        String urlFoto,
        String nombre,
        Ubicacion Lugar
) {
}
