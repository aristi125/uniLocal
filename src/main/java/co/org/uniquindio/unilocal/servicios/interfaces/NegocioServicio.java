package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.Negocio.ActualizarNegocioDTO;
import co.org.uniquindio.unilocal.dto.Negocio.RegistroNegocioDTO;
import co.org.uniquindio.unilocal.dto.Negocio.ReporteDTO;
import co.org.uniquindio.unilocal.modelo.entidades.HistorialRevision;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface NegocioServicio {
    String crearNegocio(RegistroNegocioDTO registroNegocioDTO, String id) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO, String id) throws Exception;

    void eliminarNegocio(String idNegocio) throws Exception;

    void buscarNegocios() throws Exception;

    List<HistorialRevision> getHistorialRevisiones(String idNegocio) throws Exception;

    void filtrarPorEstado() throws Exception;

    void listarNegociosPropietario() throws Exception;

    void cambiarEstado() throws Exception;

    void registrarRevision() throws Exception;

    void generarPDF(ReporteDTO reporteDTO, String rutaArchivo) throws IOException;

}
