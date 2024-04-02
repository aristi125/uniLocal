package co.org.uniquindio.unilocal.modelo.Servicios.interfaces;

import co.org.uniquindio.unilocal.dto.clienteDTO.ActualizarClienteDTO;
import co.org.uniquindio.unilocal.dto.clienteDTO.DetalleClienteDTO;
import co.org.uniquindio.unilocal.dto.clienteDTO.ItemClienteDTO;
import co.org.uniquindio.unilocal.dto.clienteDTO.RegistroClienteDTO;

import java.util.List;

public interface ClienteServicio extends CuentaServicio{
    String registrarCliente(RegistroClienteDTO registroClienteDTO)throws Exception;
    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO)throws Exception;
    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;
    void eliminarCliente(String idCuenta)throws Exception;
    List<ItemClienteDTO> listarClientes();
