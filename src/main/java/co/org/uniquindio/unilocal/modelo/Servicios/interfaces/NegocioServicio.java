package co.org.uniquindio.unilocal.modelo.Servicios.interfaces;

import co.org.uniquindio.unilocal.dto.NegocioDTO.ActualizarNegocioDTO;
import co.org.uniquindio.unilocal.dto.NegocioDTO.RegistroNegocioDTO;

public interface NegocioServicio {
    String crearNegocio(RegistroNegocioDTO registroNegocioDTO, String id) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO, String id) throws Exception;

    void eliminarNegocio(String idNegocio) throws Exception;

    void buscarNegocios() throws Exception;

    void filtrarPorEstado() throws Exception;

    void listarNegociosPropietario() throws Exception;

    void cambiarEstado() throws Exception;

    void registrarRevision() throws Exception;
}
