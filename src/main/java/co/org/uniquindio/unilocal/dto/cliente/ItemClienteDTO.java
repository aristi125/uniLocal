package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;

public record ItemClienteDTO(
        String codigo,
        String nombre,
        String fotoPerfil,
        String nickname,
        Ciudades ciudad
) {
}
