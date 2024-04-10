package co.org.uniquindio.unilocal.modelo.documentos;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document("moderadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Moderador extends Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;
}