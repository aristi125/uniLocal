package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistroAgendaDTO;


public interface AgendaServicio {

    void registrarAgenda(RegistroAgendaDTO registroAgendaDTO) throws Exception;

    void actualizarAgenda(RegistroAgendaDTO registroAgendaDTO) throws Exception;

    void eliminarAgenda(String codigoNegocio) throws Exception;

    DetalleAgendaDTO obtenerAgenda(String codigoNegocio) throws Exception;

}
