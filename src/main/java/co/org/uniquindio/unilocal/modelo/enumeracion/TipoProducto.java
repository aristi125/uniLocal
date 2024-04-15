package co.org.uniquindio.unilocal.modelo.enumeracion;

public enum TipoProducto {
    BEBIDAS("Bebidas"),
    ROPA("Ropa"),
    DECORACION("Decoración"),
    ARTE("Arte"),
    LIBROS("Libros"),
    HOSPEDAJE("Hospedaje"),
    OTRO("Otro");

    private final String descripcion;

    TipoProducto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
