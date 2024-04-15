package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;

public record ActualizarClienteDTO(

        String id,
        String nombre,
        String fotoPerfil,
        String nickname,
        String email,
        Ciudades ciudadResidencia
) {
}
