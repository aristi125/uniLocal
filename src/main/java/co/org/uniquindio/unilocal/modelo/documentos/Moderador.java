package co.org.uniquindio.unilocal.modelo.documentos;


import co.org.uniquindio.unilocal.modelo.entidades.Cuenta;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document("moderadores")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Moderador extends Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String fotoPerfil;
    private String nickname;
    private Ciudades ciudadResidencia;


}
