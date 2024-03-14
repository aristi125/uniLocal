package co.org.uniquindio.unilocal.modelo.enumeracion;

import lombok.Getter;

@Getter
public enum EstadoCuenta {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private String nombreEstado;

    EstadoCuenta(String nombreEstado){
        this.nombreEstado = nombreEstado;
    }
}
