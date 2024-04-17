package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistrarAgendaDTO;

public interface AgendaServicio {

    void registrarAgenda(RegistrarAgendaDTO registrarAgendaDTO) throws Exception;

    void actualizarAgenda(RegistrarAgendaDTO registrarAgendaDTO) throws Exception;

    void eliminarAgenda(String codigoNegocio) throws Exception;

    DetalleAgendaDTO obtenerAgenda(String codigoNegocio) throws Exception;

}
