package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

//Esta clase es para actualizar un cliente, excepto la contraseña
//por que la contraseña se actualiza en otro proceso
//y tampoco el nickname pero esta en la logica clienteServicioImpl
public record ActualizarClienteDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 30) String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank @Length(max = 30) String nickname,
        @NotBlank @Email String email,
        @NotBlank Ciudades ciudadResidencia
) {
}
