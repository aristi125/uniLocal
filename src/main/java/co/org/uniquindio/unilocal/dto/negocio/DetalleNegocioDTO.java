package co.org.uniquindio.unilocal.dto.negocio;

import co.org.uniquindio.unilocal.modelo.entidades.Agenda;
import co.org.uniquindio.unilocal.modelo.entidades.Horario;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record DetalleNegocioDTO(
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotNull List<String> telefonos,
        @NotNull Ubicacion ubicacion,
        @NotNull List<Horario>horarios,
        @NotNull CategoriaNegocio categoriaNegocio,
        @NotNull List<String>listaImagenes,
        @NotNull Agenda agenda,
        @PositiveOrZero int calificaciones


) {


}
