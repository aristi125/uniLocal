package co.org.uniquindio.unilocal.modelo.enumeracion;

import lombok.Getter;

@Getter
public enum CategoriaNegocio {
    RESTAURANTE ("Restaurante"),
    CAFETERIA ("Cafetería"),
    COMIDA_RAPIDA ("Comida Rápida"),
    MUSEO ("Museo"),
    HOTEL ("Hotel");

    private String nombre;

    CategoriaNegocio(String nombre){
        this.nombre = nombre;
    }
}
