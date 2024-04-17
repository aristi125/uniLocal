package co.org.uniquindio.unilocal.servicios.interfaces;

<<<<<<< HEAD
import co.org.uniquindio.unilocal.dto.Negocio.ActualizarNegocioDTO;
import co.org.uniquindio.unilocal.dto.Negocio.RegistroNegocioDTO;
import co.org.uniquindio.unilocal.dto.Negocio.ReporteDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
=======
import co.org.uniquindio.unilocal.dto.negocio.ActualizarNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.RegistroNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.ReporteDTO;
>>>>>>> 46c14894fe023487caa577b2dde24fc79c683893
import co.org.uniquindio.unilocal.modelo.entidades.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;

import java.io.IOException;
import java.util.List;

public interface NegocioServicio {
    String crearNegocio(RegistroNegocioDTO registroNegocioDTO, String id) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO, String id) throws Exception;

    void eliminarNegocio(String idNegocio) throws Exception;

    List<Negocio> buscarNegocios() throws Exception;

    List<Negocio> filtrarPorEstado(EstadoNegocio estadoNegocio) throws Exception;

    List<Negocio> listarNegociosPropietario(String codigoPropietario) throws Exception;

    void cambiarEstado() throws Exception;

    void registrarRevision() throws Exception;

    void generarPDF(ReporteDTO reporteDTO, String rutaArchivo) throws IOException;

}
