package co.org.uniquindio.unilocal.dto.Negocio;

import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record RegistroReservaDTO (

        @NotBlank @Length(max = 10) LocalDateTime fecha,
        @NotBlank @Length(max = 2) int hora,
        @NotBlank @Length(max = 2)  int cantidadPersonas,
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio

) {
}
