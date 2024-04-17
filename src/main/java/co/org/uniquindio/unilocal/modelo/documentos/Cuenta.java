package co.org.uniquindio.unilocal.modelo.documentos;


import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Cuenta {

    private String codigo;

    private String email;

    private String nombre;

    private String password;

    private EstadoCuenta estado;
}
