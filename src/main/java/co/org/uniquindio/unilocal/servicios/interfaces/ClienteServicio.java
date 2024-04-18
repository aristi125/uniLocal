package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.Categoria;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;

import java.util.List;

public interface ClienteServicio  {
    String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception;

    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;

    void eliminarCliente(String idCuenta) throws Exception;

    List<ItemDetalleClienteDTO> listarClientes() throws Exception;

    void agregarFavoritos(String idNegocio, String idCliente) throws Exception;

    List<FavoritoDTO> mostrarFavoritos(String idCliente) throws Exception;

    void removerFavoritos(String idNegocio, String idCliente) throws Exception;

    List<ItemListaLugaresCreadosDTO> listaLugaresCreados(String idCliente, String idNegocio) throws Exception;

    List<ItemListaLugaresCreadosDTO> buscarNegocioCategoria(Categoria categoria) throws Exception;
    List<ItemListaLugaresCreadosDTO> buscarNegocioNombre(String nombre) throws Exception;
    List<ItemListaLugaresCreadosDTO> buscarNegocioDistancia(double distancia, Ubicacion ubicacionCliente) throws Exception;
    List<ItemListaLugaresCreadosDTO> recomendarNegocio(String busqueda) throws Exception;
    List<ItemListaLugaresCreadosDTO> filtrarPorEstado(EstadoNegocio estadoNegocio)throws Exception;


}