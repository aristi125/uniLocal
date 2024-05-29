package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.BusquedaDistanciaDTO;
import co.org.uniquindio.unilocal.dto.BusquedaNombreDTO;
import co.org.uniquindio.unilocal.dto.CategoriaNegocioDTO;
import co.org.uniquindio.unilocal.dto.EstadoNegocioDTO;
import co.org.uniquindio.unilocal.dto.Moderador.RevisionesModeradorDTO;
import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistroAgendaDTO;
import co.org.uniquindio.unilocal.dto.cliente.ItemListaLugaresCreadosDTO;
import co.org.uniquindio.unilocal.dto.negocio.*;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;

import java.util.List;

public interface NegocioServicio {

    String crearNegocio(RegistroNegocioDTO registroNegocioDTO) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    void eliminarNegocio(IDClienteYNegocioDTO eliminacionNegocioDTO) throws Exception;

    Negocio buscarNegocio(String codigoNegocio) throws Exception;

    DetalleNegocioDTO obtenerNegocio(String codigoNegocio) throws Exception;

    List<Negocio> listarNegociosPropietario(String codigoPropietario) throws Exception;

    void generarPDF(ReporteDTO reporteDTO) throws Exception;

    List<ItemListaLugaresCreadosDTO> listarLugaresCreados(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception;

    List<ItemNegocioDTO> filtrarPorEstado(EstadoNegocio estadoNegocioDTO)throws Exception;

    List<ItemNegocioDTO> buscarNegocioCategoria(CategoriaNegocio categoriaNegocioDTO) throws Exception;

    List<ItemListaLugaresCreadosDTO> buscarNegocioDistancia(BusquedaDistanciaDTO busquedaDistanciaDTO) throws Exception;

    List<ItemNegocioDTO> buscarNegocioNombre(String nombre) throws Exception;

    List<ItemListaLugaresCreadosDTO> recomendarNegocio(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception;

    void registrarAgenda(RegistroAgendaDTO registroAgendaDTO) throws Exception;

    void actualizarAgenda(RegistroAgendaDTO registroAgendaDTO) throws Exception;

    void eliminarAgenda(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception;

    DetalleAgendaDTO obtenerAgenda(String codigoNegocio) throws Exception;

    void registrarReserva(DetalleReservaDTO registroReservaDTO) throws Exception;

    void actualizarReserva(DetalleReservaDTO actualizarReservaDTO) throws Exception;

    DetalleReservaDTO obtenerReserva(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception;

    void eliminarReserva(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception;

    List<DetalleReservaDTO> listarReservas(String idNegocio) throws Exception;

    List<HistorialRevision> obtenerHistorialRevisiones(String idNegocio) throws Exception;

    void rechazarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception;

    void aprobarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception;

    Cliente obtenerClienteNegocio (String idNegocio) throws Exception;

    ItemNegocioDTO verDetalleNegocio(String codigoNegocio) throws Exception;

    List<ItemNegocioDTO> listarNegocios();

    List<CategoriaNegocio> listarCategoriaNegocio() throws Exception;

}
