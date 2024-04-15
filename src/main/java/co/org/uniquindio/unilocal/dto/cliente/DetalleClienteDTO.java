package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;


//NOTA: Esta clase es para obtener el cliente con todos sus atributos
//es para hacer procesos
public record DetalleClienteDTO(
        String id,
        String nombre,
        String fotoPerfil,
        String nickname,
        String password,
        String email,
        Ciudades ciudadResidencia
) {
}
