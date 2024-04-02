package co.org.uniquindio.unilocal.modelo.documentos;

import co.org.uniquindio.unilocal.modelo.entidades.DetalleProducto;
import co.org.uniquindio.unilocal.modelo.entidades.Pago;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document("transacciones")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaccion implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private List<DetalleProducto> productos;
    private String clientes;
    private LocalDateTime fecha;
    private Pago pago;
}
