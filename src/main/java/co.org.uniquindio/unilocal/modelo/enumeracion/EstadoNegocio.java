package co.org.uniquindio.unilocal.modelo.enumeracion;

import lombok.Getter;

@Getter
public enum EstadoNegocio {
    APROBADO("Aprobado"),
    RECHAZADO("Rechazado"),
    PENDIENTE("Pendiente");


    private String estadoNegocio;

    EstadoNegocio(String estadoNegocio){
        this.estadoNegocio = estadoNegocio;
    }
}
