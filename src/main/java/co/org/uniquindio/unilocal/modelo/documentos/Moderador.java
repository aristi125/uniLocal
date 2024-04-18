package co.org.uniquindio.unilocal.modelo.documentos;


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

}
