package co.org.uniquindio.unilocal.dto.producto;

import co.org.uniquindio.unilocal.modelo.enumeracion.TipoProducto;

import java.util.List;

public record ProductoDTO (
    String codigo,
    String nombre,
    List<TipoProducto> tipoProducto,
    float precio
){
}
