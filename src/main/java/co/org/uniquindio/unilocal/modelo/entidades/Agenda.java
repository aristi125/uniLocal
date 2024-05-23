package co.org.uniquindio.unilocal.modelo.entidades;

import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoAgenda;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Agenda {

    private String tematica;

    private String descripcion;

    private EstadoAgenda estadoAgenda;
}
