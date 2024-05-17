package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.Moderador.RevisionesModeradorDTO;
import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistroAgendaDTO;
import co.org.uniquindio.unilocal.dto.cliente.FavoritoDTO;
import co.org.uniquindio.unilocal.dto.cliente.ItemListaLugaresCreadosDTO;
import co.org.uniquindio.unilocal.dto.negocio.*;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;

import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;

import java.io.IOException;
import java.util.List;

public interface NegocioServicio {
    String crearNegocio(RegistroNegocioDTO registroNegocioDTO) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    void eliminarNegocio(EliminacionNegocioDTO eliminacionNegocioDTO) throws Exception;

    Negocio buscarNegocio(String codigoNegocio) throws Exception;

    List<Negocio> listarNegociosPropietario(String codigoPropietario) throws Exception;

    void generarPDF(ReporteDTO reporteDTO, String rutaArchivo) throws Exception;

    List<Negocio> listarNegociosEstado(EstadoNegocio estadoNegocio) throws Exception;

    void eliminarNegocioRechazado() throws Exception;
    List<ItemListaLugaresCreadosDTO> listaLugaresCreados(String idCliente, String idNegocio) throws Exception;


    List<ItemListaLugaresCreadosDTO> filtrarPorEstado(EstadoNegocio estadoNegocio)throws Exception;


    List<ItemListaLugaresCreadosDTO> buscarNegocioCategoria(CategoriaNegocio categoria) throws Exception;

    List<ItemListaLugaresCreadosDTO> buscarNegocioDistancia(double distancia, Ubicacion ubicacionCliente) throws Exception;

    List<ItemListaLugaresCreadosDTO> buscarNegocioNombre(String nombre) throws Exception;

    List<ItemListaLugaresCreadosDTO> recomendarNegocio(String busqueda) throws Exception;

    void registrarAgenda(RegistroAgendaDTO registroAgendaDTO) throws Exception;

    void actualizarAgenda(RegistroAgendaDTO registroAgendaDTO) throws Exception;

    void eliminarAgenda(String codigoNegocio) throws Exception;

    DetalleAgendaDTO obtenerAgenda(String codigoNegocio) throws Exception;

    void agregarFavoritos(String idNegocio, String idCliente) throws Exception;

    List<FavoritoDTO> mostrarFavoritos(String idCliente) throws Exception;

    void removerFavoritos(String idNegocio, String idCliente) throws Exception;

    void registrarReserva(DetalleReservaDTO registroReservaDTO) throws Exception;

    void actualizarReserva(DetalleReservaDTO actualizarReservaDTO) throws Exception;

    DetalleReservaDTO obtenerReserva(String idNegocio, String idCliente) throws Exception;

    void eliminarReserva(String idNegocio, String idCliente) throws Exception;

    List<DetalleReservaDTO> listarReservas(String idNegocio) throws Exception;

    List<HistorialRevision> obtenerHistorialRevisiones(String idNegocio) throws Exception;

    void rechazarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception;

    void aprobarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception;

    Cliente obtenerClienteNegocio (String idNegocio) throws Exception;
}
