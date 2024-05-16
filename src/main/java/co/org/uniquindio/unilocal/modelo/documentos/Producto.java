package co.org.uniquindio.unilocal.modelo.documentos;

import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoProducto;
import co.org.uniquindio.unilocal.modelo.enumeracion.TipoProducto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document("productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String nombre;
    private List<TipoProducto> tipoProducto = new ArrayList<>(); // Se inicia la variable para evitar una excepción
    private float precio;
    private EstadoProducto estadoProducto;
}
