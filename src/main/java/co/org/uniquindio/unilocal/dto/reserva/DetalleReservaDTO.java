package co.org.uniquindio.unilocal.dto.reserva;

import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;

import java.time.LocalDateTime;

public class DetalleReservaDTO {

    LocalDateTime fecha;
    int hora;
    int cantidadPersonas;
    Cliente cliente;
    Negocio negocio;

}
