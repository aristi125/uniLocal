package co.org.uniquindio.unilocal.modelo.enumeracion;

public enum TipoProducto {
    BEBIDAS("Bebidas"),
    ARTE("Arte"),
    HOSPEDAJE("Hospedaje");

    private final String descripcion;

    TipoProducto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
