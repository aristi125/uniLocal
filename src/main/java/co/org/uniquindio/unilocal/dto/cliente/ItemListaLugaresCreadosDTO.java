package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.enumeracion.Categoria;

import java.util.List;

public record ItemListaLugaresCreadosDTO(
        String idNegocio,
        String nombre,
        List<String> telefono,
        Categoria categoria,
        List<String> urlFoto
) {
}
