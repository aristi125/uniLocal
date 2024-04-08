package co.org.uniquindio.unilocal.modelo.entidades;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Horario {

    private String dia;

    private LocalDate horaInicio;

    private LocalDate horaFin;
}
