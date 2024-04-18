package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;

import java.util.List;

//Esta clase es para obtener los lugares creados por el cliente
public record ItemListaLugaresCreadosDTO(
        String idNegocio,
        String nombre,
        List<String> telefono,
        CategoriaNegocio categoriaNegocio,
        List<String> urlFoto
) {
}
