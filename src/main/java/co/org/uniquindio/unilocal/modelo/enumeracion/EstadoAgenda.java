package co.org.uniquindio.unilocal.modelo.enumeracion;

public enum EstadoAgenda {
    ACTIVA ("Activa"),
    INACTIVA ("Inactiva");

    private String nombre;

    EstadoAgenda(String nombre){
        this.nombre = nombre;
    }
}
