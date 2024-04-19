package co.org.uniquindio.unilocal.dto.negocio;

import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ActualizarNegocioDTO(
       @NotBlank String id,
       @NotBlank @Length(max = 30) String nombre,
       @NotBlank String descripcion,
       @NotNull List<Horario> horarios,
       @NotNull List<String> telefonos,
       @NotNull CategoriaNegocio categoriaNegocio,
       @NotBlank String urlFoto,
       @NotBlank String codigoCliente
) {
}
