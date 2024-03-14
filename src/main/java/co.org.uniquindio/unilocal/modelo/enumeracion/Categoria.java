package co.org.uniquindio.unilocal.modelo.enumeracion;

import lombok.Getter;

@Getter
public enum Categoria {
    RESTAURANTE ("Restaurante"),
    CAFETERIA ("Cafetería"),
    COMIDA_RAPIDA ("Comida Rápida"),
    MUSEO ("Museo"),
    HOTEL ("Hotel");

    private String nombre;

    Categoria(String nombre){
        this.nombre = nombre;
    }
}
