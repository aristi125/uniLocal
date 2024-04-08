package co.org.uniquindio.unilocal.dto.Usuario;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record cambioPasswordDTO(
        @NotBlank @Length(min = 7) String nuevaPassword,
        @NotBlank String codigo,
        String token
) {
}
