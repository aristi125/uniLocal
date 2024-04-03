package co.org.uniquindio.unilocal.servicios.interfaces;

import co.org.uniquindio.unilocal.dto.Negocio.ReporteDTO;

import java.io.IOException;

public interface NegocioServicio {

    void generarPDF(ReporteDTO reporteDTO, String rutaArchivo) throws IOException;
}
