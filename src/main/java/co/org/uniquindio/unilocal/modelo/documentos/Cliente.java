package co.org.uniquindio.unilocal.modelo.documentos;

import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("clientes")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    //LA CEDULA NO ES NECESARIA
    private String cedula;
    private String nombre;
    private String nickname;
    private Ciudades ciudad;
    private String fotoPerfil;

    private String password;
    private String email;
    //LOS TELEFONOS NO SON NECESARIOS
    private List<String> telefono;
    private EstadoCuenta estado;

    //AGREGAR A FAVORITOS
    private List<String> agregarFavoritos;

    //ESTO ES PARA EL TEST
    @Builder
    public Cliente(String cedula, String nombre, String email, List<String> telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
}
