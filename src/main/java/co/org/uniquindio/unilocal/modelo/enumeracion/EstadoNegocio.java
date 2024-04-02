package co.org.uniquindio.unilocal.modelo.enumeracion;

public enum EstadoNegocio {
    APROBADO("Aprobado"),
    RECHAZADO("Rechazado"),
    PENDIENTE("Pendiente");


    private String estadoNegocio;

    EstadoNegocio(String estadoNegocio){
        this.estadoNegocio = estadoNegocio;
    }
}
