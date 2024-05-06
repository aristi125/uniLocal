package co.org.uniquindio.unilocal.modelo.entidades;


import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuenta implements Serializable {

    private String email;

    private String nombre;

    private String password;

    private EstadoCuenta estado;
}
