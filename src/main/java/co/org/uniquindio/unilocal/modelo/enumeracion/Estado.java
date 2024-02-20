package co.org.uniquindio.unilocal.modelo.enumeracion;

import lombok.Getter;

@Getter
public enum Estado {
    SIN_VRIFICAR ("Sin verificar"),
    EXISTENTE ("Existente"),
    INEXISTENTE ("Inexistente");

    private String nombreEstado;

    Estado(String nombreEstado){
        this.nombreEstado = nombreEstado;
    }
}
