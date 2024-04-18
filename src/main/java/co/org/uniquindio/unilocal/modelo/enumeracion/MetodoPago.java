package co.org.uniquindio.unilocal.modelo.enumeracion;

public enum MetodoPago {
    EFECTIVO("Efectivo"),
    PSE("PSE"),
    TARJETA("Tarjeta");

    private String nombreEstado;

    MetodoPago(String nombreEstado){
        this.nombreEstado = nombreEstado;
    }
}
