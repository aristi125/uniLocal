package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;

//NOTA: Esta clase es para obtener el cliente con todos sus atributos EXCEPTO el nickname y la contraseña
// es para listar
public record ItemDetalleClienteDTO (
        String id,
        String nombre,
        String fotoPerfil,
        String email,
        Ciudades ciudadResidencia
) {

}
