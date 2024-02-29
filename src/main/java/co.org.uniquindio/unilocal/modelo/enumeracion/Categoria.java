package co.org.uniquindio.unilocal.modelo.enumeracion;

import lombok.Getter;

@Getter
public enum Categoria {
    RESTAURANTE ("restaurante"),
    CAFETERIA ("Cafeteria"),
    COMIDA_RAPIDA ("Comida Rapida"),
    MUSEO ("Museo"),
    HOTEL ("Hotel");

    private String nombre;

    Categoria(String nombre){
        this.nombre = nombre;
    }
}
