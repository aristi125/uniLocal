package co.org.uniquindio.unilocal.modelo.enumeracion;

public enum RolUsuario {
    MODERADOR("Moderador"),
    CLIENTE("cliente");

    private String nombreEstado;

    RolUsuario(String nombreEstado){
        this.nombreEstado = nombreEstado;
    }
}
