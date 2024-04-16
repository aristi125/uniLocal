package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.Cuenta.CambioPasswordDTO;

import java.util.List;

public interface ClienteServicio  {
    String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception;

    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;

    void eliminarCliente(String idCuenta) throws Exception;

    List<ItemDetalleClienteDTO> listarClientes() throws Exception;

    void enviarLinkRecuperacion(String email) throws Exception;

    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception;

    void agregarFavoritos(String idNegocio, String idCliente) throws Exception;

    List<FavoritoDTO> mostrarFavoritos(String idCliente) throws Exception;

    void removerFavoritos(String idNegocio, String idCliente) throws Exception;

    List<ItemListaLugaresCreadosDTO> listaLugaresCreados(String idCliente, String idNegocio) throws Exception;
}