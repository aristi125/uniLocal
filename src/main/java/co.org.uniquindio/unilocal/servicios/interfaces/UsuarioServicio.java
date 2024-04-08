package co.org.uniquindio.unilocal.servicios.interfaces;


import co.org.uniquindio.unilocal.dto.Usuario.*;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;

import java.util.List;

public interface UsuarioServicio {

    String registarUsuario(RegistroUsuarioDTO registroUsuarioDTO) throws Exception;
    void actualizarUsuario(ActualizarUsuarioDTO actualizarUsuarioDTO)throws Exception;
    DetalleUsuarioDTO obtenerUsuario(String codigo) throws Exception;
    void eliminarUsuario(String codigo)throws Exception;
    List<ItemUsuarioDTO> listarUsuario();
    void recuperarPassword(cambioPasswordDTO cambioPasswordDTO) throws Exception;
    List<Negocio> listarLugaresCercanos();
    List<Negocio> listarLugaresCreados();
    void comentarNegocio() throws Exception;
    void calificarNegocio() throws Exception;
}
