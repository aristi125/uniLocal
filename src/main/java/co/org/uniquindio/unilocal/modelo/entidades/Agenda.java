package co.org.uniquindio.unilocal.modelo.entidades;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Agenda {

    private  String codigoNegocio;

    private String nombreNegocio;

    private String tematica;

    private String descripcion;
}