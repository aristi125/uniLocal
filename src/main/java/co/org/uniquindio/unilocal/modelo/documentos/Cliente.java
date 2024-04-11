package co.org.uniquindio.unilocal.modelo.documentos;

import co.org.uniquindio.unilocal.modelo.entidades.Cuenta;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
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
public class Cliente extends Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    //LA CEDULA NO ES NECESARIA
    private String cedula;
    private String nickname;
    private Ciudades ciudad;
    private String fotoPerfil;

    //LOS TELEFONOS NO SON NECESARIOS
    private List<String> telefono;


    //AGREGAR A FAVORITOS
    private List<String> agregarFavoritos;

    //ESTO ES PARA EL TEST
    @Builder
    public Cliente(String cedula, String nombre, String email, List<String> telefono) {
        this.cedula = cedula;
        this.setNombre(nombre);
        this.setEmail(email);
        this.telefono = telefono;
    }
}
