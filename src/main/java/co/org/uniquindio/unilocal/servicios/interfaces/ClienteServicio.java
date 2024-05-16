package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;

import java.util.List;
import java.util.Optional;

public interface ClienteServicio  {
    String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception;

    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;

    void eliminarCliente(String idCuenta) throws Exception;

    List<ItemDetalleClienteDTO> listarClientes() throws Exception;

    void enviarLinkRecuperacion(String email)throws Exception;

    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;

    Cliente buscarCliente(String idCuenta) throws Exception;

}