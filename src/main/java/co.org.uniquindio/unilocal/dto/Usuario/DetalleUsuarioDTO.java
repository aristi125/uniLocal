package co.org.uniquindio.unilocal.dto.Usuario;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record DetalleUsuarioDTO(
        @NotBlank String codigo,
        @NotBlank @Length(max = 70) String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank @Length(max = 15) String nickname,
        @NotBlank @Length(max = 40) @Email String email,
        @NotBlank Ciudades ciudadResidencia
) {
}
