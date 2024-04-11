package co.org.uniquindio.unilocal.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.Email;

public record EmailDTO (
        @NotBlank @Length(max = 40) String asunto,
        @NotBlank @Length(max = 100) String cuerpo,
        @NotBlank @Email @Length(max = 100) String destinatario
) {
}
