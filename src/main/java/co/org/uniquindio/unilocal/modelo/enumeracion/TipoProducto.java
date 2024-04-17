package co.org.uniquindio.unilocal.modelo.enumeracion;

public enum TipoProducto {
    BEBIDAS("Bebidas"),
    EXPOSICION_ARTE("Exposición arte"),
    HOSPEDAJE("Hospedaje"),
    HAMBURGUESA("Hamburguesa"),
    PIZZAS("Pizzas"),
    ALMUERZO_EJECUTIVO("Almuerzo ejecutivo"),
    POSTRES("Postres"),
    CAFE_DESCAFEINADO("Café Descafeinado"),
    CAFE_CON_LECHE("Café con leche");

    private final String descripcion;

    TipoProducto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
