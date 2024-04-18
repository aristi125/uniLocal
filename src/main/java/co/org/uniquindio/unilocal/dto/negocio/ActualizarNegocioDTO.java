package co.org.uniquindio.unilocal.dto.negocio;

import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ActualizarNegocioDTO(
       @NotBlank String id,
       @NotBlank  String nombre,
        String descripcion,
        List<Horario> horarios,
        List<String> telefonos,
        CategoriaNegocio categoriaNegocio,
        String urlFoto,

        String codigoCliente
) {
}