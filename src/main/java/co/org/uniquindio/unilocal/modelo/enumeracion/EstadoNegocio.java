package co.org.uniquindio.unilocal.modelo.enumeracion;

public enum EstadoNegocio {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");
    private String estadoNegocio;

    EstadoNegocio(String estadoNegocio){

        this.estadoNegocio = estadoNegocio;
    }
}
