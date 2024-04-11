package co.org.uniquindio.unilocal.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
=======
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailDTO (
        @NotBlank @Length(max = 40) String asunto,
        @NotBlank @Length(max = 100) String cuerpo,
        @NotBlank @Email @Length(max = 100) String destinatario
) {
>>>>>>> d6446126fca80ffa023d2ee65ed18ff476e528f7

public record EmailDTO  (
    @NotBlank @Length(min = 50) String asunto,
    String cuerpo,
    String destinatario
    ) {
}
