package co.org.uniquindio.unilocal.modelo.documentos;

import co.org.uniquindio.unilocal.modelo.enumeracion.TipoProducto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
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
    private List<TipoProducto> tipoProducto;
    private float precio;
}
