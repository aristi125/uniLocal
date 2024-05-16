package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.cuenta.LinkRecuperacionDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;

import java.util.List;
import java.util.Optional;

public interface ClienteServicio  {
    String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception;

    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;

    void eliminarCliente(String idCuenta) throws Exception;

    List<ItemDetalleClienteDTO> listarClientes() throws Exception;

    void enviarLinkRecuperacionCliente(LinkRecuperacionDTO linkRecuperacionDTO)throws Exception;

    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;

    Cliente buscarCliente(String idCuenta) throws Exception;

    void bloquearUsuario(String codigo) throws Exception;


}