package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//Esta clase es para actualizar un cliente, excepto la contraseña
//por que la contraseña se actualiza en otro proceso
//y tampoco el nickname pero esta en la logica clienteServicioImpl
public record ActualizarClienteDTO(
        String id,
        String nombre,
        String fotoPerfil,
        String nickname,
        @Email String email,
        @NotBlank Ciudades ciudadResidencia
) {
}
