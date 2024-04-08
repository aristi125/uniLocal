package co.org.uniquindio.unilocal.modelo.enumeracion;

public enum EstadoFavorito {
    FAVORITO ("Favorito"),
    DESFAVORITO("Desfavorito");

    private String estadoFavorito;

    EstadoFavorito(String estadoFavorito){
        this.estadoFavorito = estadoFavorito;
    }
}
