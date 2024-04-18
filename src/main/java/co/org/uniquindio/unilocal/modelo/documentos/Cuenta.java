package co.org.uniquindio.unilocal.modelo.documentos;


import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("cuentas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String email;

    private String nombre;

    private String password;

    private EstadoCuenta estado;
}
