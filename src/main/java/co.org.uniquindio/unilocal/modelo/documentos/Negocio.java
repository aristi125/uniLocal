package co.org.uniquindio.unilocal.modelo.documentos;

import co.org.uniquindio.unilocal.modelo.entidades.Calificacion;
import co.org.uniquindio.unilocal.modelo.entidades.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.enumeracion.Categoria;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
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
    private String codigo;
    //atributos
    private String nombre;
    private String descripcion;
    private Categoria categoriaNegocio;
    //listas
    private List<String> imagenes;
    private List<Horario> horarios;
    private List<String> telefonos;
    private List<Comentario> comentarios;
    private List<Calificacion> calificaciones;
    private List<HistorialRevision> historialRevisiones;
    private String codigoCliente;
    private Categoria categoriaLugar;



    private EstadoCuenta estado;

}
