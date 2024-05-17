package co.org.uniquindio.unilocal.dto.Moderador;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public record DetalleModeradorDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 30) String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank @Length(max = 30) String nickname,
        @NotBlank @Email String email,
        @NotNull Ciudades ciudadResidencia
) {
}
