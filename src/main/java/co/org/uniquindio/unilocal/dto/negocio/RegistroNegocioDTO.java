package co.org.uniquindio.unilocal.dto.negocio;

import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;

import java.util.List;

public record RegistroNegocioDTO(
        String id,
        String nombre,
        String descripcion,
        List<Horario> horarios,
        List<String> telefono,
        CategoriaNegocio categoriaNegocio,
        List<String> urlFoto,
        double longitud,
        double latitud

) {
}
