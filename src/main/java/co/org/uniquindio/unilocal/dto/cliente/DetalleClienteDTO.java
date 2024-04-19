package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


//NOTA: Esta clase es para obtener el cliente con todos sus atributos
//es para hacer procesos
public record DetalleClienteDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 30) String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank @Length(max = 30) String nickname,
        @NotBlank @Length(min = 8) String password,
        @NotBlank @Email String email,
        @NotNull Ciudades ciudadResidencia
) {
}
