package co.org.uniquindio.unilocal.modelo.enumeracion;

import lombok.Getter;

@Getter
public enum Ciudades {

    ARMENIA ("Armenia"),
    PEREIRA ("Pereira"),
    CALI ("Cali"),
    MEDELLIN ("Medellin"),
    BOGOTA ("Bogota"),
    BUCARAMANGA ("Bucaramanga"),
    CARTAGENA("Cartagena");

    private String nombreCiudad;

    Ciudades(String nombreCiudad){
        this.nombreCiudad = nombreCiudad;
    }
}
