package co.org.uniquindio.unilocal.dto.Usuario;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemUsuarioDTO(
    @NotBlank String codigo,
    @NotBlank @Length(max = 70) String nombre,
    @NotBlank @Length(max = 15) String nickname,
    @NotBlank String fotoPerfil,
    @NotBlank Ciudades ciudadResisdencia
) {
}
