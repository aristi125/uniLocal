package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.Categoria;

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

    List<ItemListaLugaresDTO> listaLugaresCreados(String idCliente, String idNegocio) throws Exception;

    List<ItemListaLugaresDTO> buscarNegocioNombre(String nombre) throws Exception;

    List<ItemListaLugaresDTO> buscarNegocioCategoria(Categoria categoria) throws Exception;

    List<ItemListaLugaresDTO> buscarNegocioDistancia(double distancia, Ubicacion ubicacionCliente) throws Exception;

    List<ItemListaLugaresDTO> recomendarNegocio(String busqueda) throws Exception;

}