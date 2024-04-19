package co.org.uniquindio.unilocal.modelo.enumeracion;

public enum EstadoNegocio {
    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    PENDIENTE("Pendiente");
    private String estadoNegocio;

    EstadoNegocio(String estadoNegocio){

        this.estadoNegocio = estadoNegocio;
    }
}
