package co.org.uniquindio.unilocal.modelo.enumeracion;

import lombok.Getter;

@Getter
public enum EstadoProducto {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private String nombreEstado;

    EstadoProducto(String nombreEstado){
        this.nombreEstado = nombreEstado;
    }
}
