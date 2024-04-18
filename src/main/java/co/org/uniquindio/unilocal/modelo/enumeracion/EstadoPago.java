package co.org.uniquindio.unilocal.modelo.enumeracion;

public enum EstadoPago {
    PENDIENTE("Pendiente"),
    CONFIRMADO("Confirmado");

    private String nombreEstado;

    EstadoPago(String nombreEstado){
        this.nombreEstado = nombreEstado;
    }
}
