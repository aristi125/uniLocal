package co.org.uniquindio.unilocal.dto.negocio;

import java.time.LocalDateTime;

public record ReporteDTO(
        String codigoNegocio,
        String nombreNegocio,
        LocalDateTime fecha,
        int numReservas
) {
}
