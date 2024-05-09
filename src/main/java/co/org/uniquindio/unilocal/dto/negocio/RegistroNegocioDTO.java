package co.org.uniquindio.unilocal.dto.negocio;

import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroNegocioDTO(
        @NotBlank @Length(max = 30) String nombre,
        @NotBlank String descripcion,
        @NotNull List<Horario> horarios,
        @NotNull List<String> telefono,
        @NotNull CategoriaNegocio categoriaNegocio,
        @NotNull List<String> urlFoto,
        @NotNull Ubicacion ubicacion,
        @NotBlank String codigoPropietario


) {
}
