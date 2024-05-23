package co.org.uniquindio.unilocal.modelo.documentos;


import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.entidades.*;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document("negocios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Negocio implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String nombre;
    private String descripcion;
    private CategoriaNegocio categoriaNegocio;
    private Ubicacion ubicacion;
    private List<String> imagenes = new ArrayList<>();
    private List<Horario> horarios = new ArrayList<>();
    private List<String> telefonos = new ArrayList<>();
    private List<Comentario> comentarios = new ArrayList<>();
    private int calificaciones;
    private List<HistorialRevision> historialRevisiones = new ArrayList<>();
    private String codigoCliente;
    private EstadoNegocio estado;
    private List<Reserva> listaReservas = new ArrayList<>();
    private Agenda agenda;

}
