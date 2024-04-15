package co.org.uniquindio.unilocal.dto;

<<<<<<< HEAD

=======
>>>>>>> 889f068e65536428932bd11e34ff1966f8da2aec
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.Email;

public record EmailDTO (
        @NotBlank @Length(max = 40) String asunto,
        @NotBlank @Length(max = 1000) String cuerpo,
        @NotBlank @Email @Length(max = 100) String destinatario
) {
<<<<<<< HEAD

=======
>>>>>>> 889f068e65536428932bd11e34ff1966f8da2aec
}
