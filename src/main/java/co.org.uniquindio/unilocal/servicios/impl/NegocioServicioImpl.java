package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.Negocio.ReporteDTO;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;


public class NegocioServicioImpl implements NegocioServicio {

    private final NegocioRepo negocioRepo;

    public NegocioServicioImpl(NegocioRepo negocioRepo) {
        this.negocioRepo = negocioRepo;
    }

    @Override
    public void generarPDF(ReporteDTO reporteDTO, String rutaArchivo) throws IOException {
        PDDocument document = null;
        try {
            // Crear un nuevo documento PDF
            document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Inicia el flujo de contenido en la pagina
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                contentStream.newLineAtOffset(100, 700);
                // Agregar los datos del ReporteDTO al PDF
                contentStream.showText("Código de Negocio: " + reporteDTO.codigoNegocio());
                contentStream.newLine();
                contentStream.showText("Nombre del Negocio: " + reporteDTO.nombreNegocio());
                contentStream.newLine();
                contentStream.showText("Fecha: " + reporteDTO.fecha());
                contentStream.newLine();
                contentStream.showText("Número de Resevas: " + reporteDTO.numReservas());
                contentStream.endText();
            }
            // Guardar el documento PDF en la ruta especifica
            document.save(rutaArchivo);
        } catch (IOException e)  {
            // Manejar errores al crear el PDF
            System.out.println("Error al crear el archivo PDF: " + e.getMessage());
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                System.out.println("El error al cerrar el documento PDF: " + e.getMessage());
            }
        }
    }


}
