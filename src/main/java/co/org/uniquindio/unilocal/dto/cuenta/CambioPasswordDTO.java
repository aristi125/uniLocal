package co.org.uniquindio.unilocal.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CambioPasswordDTO(
      @NotBlank String idCuenta,
      @NotBlank @Length(min=8) String passwordNueva,
      @NotBlank @Email String email,
      @NotBlank String token
) {
}
