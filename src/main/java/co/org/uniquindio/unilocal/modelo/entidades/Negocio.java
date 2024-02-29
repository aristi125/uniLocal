package co.org.uniquindio.unilocal.modelo.entidades;

import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoRegistro;
import co.org.uniquindio.unilocal.modelo.enumeracion.TipoNegocio;

import java.util.List;

public class Negocio {

    private Ubicacion ubicacion;
    private String nombre;
    private String descripcion;
    private List<Horario> horarios;
    private List<String> imagenes;
    private String codigoNegocio;
    private String codigoCliente;
    private TipoNegocio tipoNegocio;

    private List<String> telefonos;

    private EstadoRegistro estado;
    private List<HistorialRevision> historialRevisiones;

}
