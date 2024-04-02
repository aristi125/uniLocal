package co.org.uniquindio.unilocal.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DetalleProducto {

    private String codigoProducto;
    private float precio;
    private int cantidad;
}
