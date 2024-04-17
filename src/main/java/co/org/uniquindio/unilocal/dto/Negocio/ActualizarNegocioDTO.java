package co.org.uniquindio.unilocal.dto.Negocio;

import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.enumeracion.Categoria;

import java.util.List;

public record ActualizarNegocioDTO(
       @NotBlank String id,
       @NotBlank  String nombre,
        String descripcion,
        List<Horario> horarios,
        List<String> telefonos,
        Categoria categoria,
        String urlFoto
) {
}
