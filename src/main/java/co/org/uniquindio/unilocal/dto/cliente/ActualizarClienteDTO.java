package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ActualizarClienteDTO(

        String id,
        String nombre,
        String fotoPerfil,
        String nickname,
        @Email String email,
        @NotBlank Ciudades ciudadResidencia
) {
}
