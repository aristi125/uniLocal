package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record RegistroClienteDTO (
        @NotBlank @Length(max = 30) String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank @Length(max = 30) String nickname,
        @NotBlank @Email String email,
        @NotBlank @Length(min = 8) String password,
        @NotNull Ciudades ciudadResidencia
) {


}