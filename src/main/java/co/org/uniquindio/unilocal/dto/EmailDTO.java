package co.org.uniquindio.unilocal.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailDTO  (
    @NotBlank @Length(min = 50) String asunto,
    String cuerpo,
    String destinatario
    ) {
}
