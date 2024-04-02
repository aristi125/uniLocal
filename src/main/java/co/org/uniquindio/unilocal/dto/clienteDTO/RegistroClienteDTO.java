package co.org.uniquindio.unilocal.dto.clienteDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegistroClienteDTO (
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank @Length(max = 10) String nickname,
        @NotBlank @Email @Length(max = 100) String email,
        @NotBlank @Length(min = 7) String password,
        @NotBlank @Length(max = 50) String ciudadResidencia
) {


}