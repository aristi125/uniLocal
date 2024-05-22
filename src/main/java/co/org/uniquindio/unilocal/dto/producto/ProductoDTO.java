package co.org.uniquindio.unilocal.dto.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ProductoDTO (
    @NotBlank String codigo,
    @NotBlank @Length(max = 30) String nombre,
    @NotNull List<TipoProducto> tipoProducto,
    @NotNull float precio,
    @NotNull String idCliente,
    @NotNull String idNegocio
){

}
