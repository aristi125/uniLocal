package co.org.uniquindio.unilocal.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CambioPasswordDTO(
      @NotBlank String id,
      @NotBlank @Length(min=8, message="La contraseña debe tener minim 8 caracteres") String passwordNueva,
      @NotBlank @Email String email,
                String token
) {
}
