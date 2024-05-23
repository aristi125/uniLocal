package co.org.uniquindio.unilocal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.File;

public record EmailArchivoDTO(
        @NotBlank @Length(max = 40) String asunto,
        @NotBlank @Length(max = 1000) String cuerpo,
        @NotBlank @Email @Length(max = 100) String destinatario,
        File archivo
) {
}
