package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

//NOTA: Esta clase es para obtener el cliente con todos sus atributos EXCEPTO el nickname y la contraseña
// es para listar
public record ItemDetalleClienteDTO (
        @NotBlank String id,
        @NotBlank @Length(max = 30) String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank @Email String email,
        @NotNull Ciudades ciudadResidencia
) {

}
