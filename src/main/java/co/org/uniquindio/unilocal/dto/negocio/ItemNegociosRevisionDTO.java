package co.org.uniquindio.unilocal.dto.negocio;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;
import java.util.Set;

public record ItemNegociosRevisionDTO(
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotBlank Set<String> telefonos,
        @NotBlank String direccion,
        //@NotBlank List<Horario>horarios,
        //@NotBlank TipoNegocio tipoNegocio,
        @NotBlank List<String>listaImagens
) {
}