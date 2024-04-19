package co.org.uniquindio.unilocal.dto.cliente;

import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

//Esta clase es para obtener los lugares creados por el cliente
public record ItemListaLugaresCreadosDTO(
        @NotBlank String idNegocio,
        @NotBlank @Length(max = 30) String nombre,
        @NotNull List<String> telefono,
        @NotNull CategoriaNegocio categoriaNegocio,
        @NotNull List<String> urlFoto
) {
}
