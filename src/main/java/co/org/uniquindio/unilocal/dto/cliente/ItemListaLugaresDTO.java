package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.Categoria;

import java.util.List;

//Esta clase es para obtener los lugares creados por el cliente
public record ItemListaLugaresDTO(
        String idNegocio,
        String nombre,
        List<String> telefono,
        Categoria categoria,
        List<String> urlFoto
) {
}
