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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Cliente extends Cuenta implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private String codigo;

    private String nickname;
    private Ciudades ciudad;
    private String fotoPerfil;

    //AGREGAR A FAVORITOS
    private List<String> agregarFavoritos;
}
