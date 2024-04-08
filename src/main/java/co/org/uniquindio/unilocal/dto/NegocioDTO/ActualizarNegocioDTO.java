package co.org.uniquindio.unilocal.dto.NegocioDTO;

import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.enumeracion.Categoria;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public record ActualizarNegocioDTO(
        String id,
        String nombre,
        String descripcion,
        List<Horario> horarios,
        List<String> telefonos,
        Categoria categoria,
        String urlFoto
) {
}
