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
@AllArgsConstructor
@ToString
public class Cliente extends Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String nickname;
    private Ciudades ciudad;
    private String fotoPerfil;

    //AGREGAR A FAVORITOS
    private List<Negocio> agregarFavoritos;
}
