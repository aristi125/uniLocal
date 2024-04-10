package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.Negocio.ActualizarNegocioDTO;
import co.org.uniquindio.unilocal.dto.Negocio.RegistroNegocioDTO;
import co.org.uniquindio.unilocal.dto.Negocio.ReporteDTO;

import java.io.IOException;

public interface NegocioServicio {
    String crearNegocio(RegistroNegocioDTO registroNegocioDTO, String id) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO, String id) throws Exception;

    void eliminarNegocio(String idNegocio) throws Exception;

    void buscarNegocios() throws Exception;

    void filtrarPorEstado() throws Exception;

    void listarNegociosPropietario() throws Exception;

    void cambiarEstado() throws Exception;

    void registrarRevision() throws Exception;

    void generarPDF(ReporteDTO reporteDTO, String rutaArchivo) throws IOException;

}
