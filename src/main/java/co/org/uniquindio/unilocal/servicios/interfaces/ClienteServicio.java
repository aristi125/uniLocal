package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.dto.cliente.*;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.cuenta.LinkRecuperacionDTO;
import co.org.uniquindio.unilocal.dto.negocio.IDClienteYNegocioDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoCuenta;

import java.util.List;
import java.util.Optional;

public interface ClienteServicio  {
    String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception;

    DetalleClienteDTO obtenerCliente(String idCuenta) throws Exception;

    void eliminarCliente(String idCuenta) throws Exception;

    List<ItemDetalleClienteDTO> listarClientes() throws Exception;

    List<Ciudades> listarCiudades() throws Exception;

    List<CategoriaNegocio> listarCategoriaNegocio() throws Exception;

    void enviarLinkRecuperacionCliente(LinkRecuperacionDTO linkRecuperacionDTO)throws Exception;

    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;

    Cliente buscarCliente(String idCuenta) throws Exception;

    void bloquearUsuario(String codigo) throws Exception;

    void agregarFavoritos(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception;

    List<FavoritoDTO> mostrarFavoritos(String idCliente) throws Exception;

    void eliminarFavoritos(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception;


}