package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.modelo.documentos.Negocio;

import co.org.uniquindio.unilocal.dto.negocio.ActualizarNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.RegistroNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.ReporteDTO;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface NegocioServicio {
    String crearNegocio(RegistroNegocioDTO registroNegocioDTO) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    void eliminarNegocio(String idNegocio) throws Exception;

    Negocio buscarNegocio(String codigoNegocio) throws Exception;

    List<Negocio> listarNegociosPropietario(String codigoPropietario) throws Exception;

    void generarPDF(ReporteDTO reporteDTO, String rutaArchivo) throws IOException;

    List<Negocio> listarNegociosEstado(EstadoNegocio estadoNegocio) throws Exception;

    void eliminarNegocioRechazado() throws Exception;

}
