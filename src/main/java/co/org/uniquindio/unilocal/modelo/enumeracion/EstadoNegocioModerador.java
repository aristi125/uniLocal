package co.org.uniquindio.unilocal.modelo.enumeracion;

import lombok.Getter;

@Getter
public enum EstadoNegocioModerador {
    APROBADO("Aprobado"),
    RECHAZADO("Rechazado"),
    PENDIENTE("Pendiente");


    private String estadoNegocio;

    EstadoNegocioModerador(String estadoNegocio){
        this.estadoNegocio = estadoNegocio;
    }
}
