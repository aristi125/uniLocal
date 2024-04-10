package co.org.uniquindio.unilocal.modelo.Servicios.interfaces;

import co.org.uniquindio.unilocal.dto.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.dto.QuienHizoComentarioDTO;
import co.org.uniquindio.unilocal.dto.clienteDTO.*;

import java.util.List;

public interface ClienteServicio extends CuentaServicio {
    String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception;

    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;

    void eliminarCliente(String idCuenta) throws Exception;

    List<ItemClienteDTO> listarClientes() throws Exception;

    void favoritos(String idNegocio, String idCliente) throws Exception;

    List<FavoritoDTO> mostrarFavoritos(String idCliente) throws Exception;

    void removerFavoritos(String idNegocio, String idCliente) throws Exception;

    List<ItemListaLugaresCreadosDTO> listaLugaresCreados(String idCliente, String idNegocio) throws Exception;

    List<ItemListaComentariosDTO> ListaComentarios(QuienHizoComentarioDTO hizoComentarioDTO) throws Exception;

}