package co.org.uniquindio.unilocal.modelo.enumeracion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum EstadoRegistro {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private String nombreEstado;

    EstadoRegistro(String nombreEstado){
        this.nombreEstado = nombreEstado;
    }
}

/*

  SIN_VRIFICAR ("Sin verificar"),
    EXISTENTE ("Existente"),
    INEXISTENTE ("Inexistente");

    private String nombreEstado;

    EstadoRegistro(String nombreEstado){
        this.nombreEstado = nombreEstado;
    }
 */