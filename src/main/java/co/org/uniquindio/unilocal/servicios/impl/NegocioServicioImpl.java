package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.negocio.ActualizarNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.RegistroNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.ReporteDTO;
import co.org.uniquindio.unilocal.modelo.documentos.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoRevision;
import co.org.uniquindio.unilocal.repositorios.HistorialRevisionRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NegocioServicioImpl implements NegocioServicio {

    private final ClienteRepo clienteRepo;
    private final NegocioRepo negocioRepo;
    private final HistorialRevisionRepo historialRepo;
    @Override
    public String crearNegocio(RegistroNegocioDTO registroNegocioDTO) throws Exception {
        // Obtener la información del usuario autenticado

        // Verificar si el usuario autenticado existe en la base de datos
        Cliente cliente = clienteRepo.findByEmail(registroNegocioDTO.codigoPropietario())
                .orElseThrow(() -> new Exception("El usuario autenticado no está registrado"));

        // Crear un nuevo negocio con la información proporcionada en el DTO
        Negocio negocio = new Negocio();
        negocio.setNombre(registroNegocioDTO.nombre());
        negocio.setDescripcion(registroNegocioDTO.descripcion());
        negocio.setHorarios(registroNegocioDTO.horarios());
        negocio.setTelefonos((registroNegocioDTO.telefono()));
        negocio.setCategoriaNegocio(registroNegocioDTO.categoriaNegocio());
        negocio.setImagenes((registroNegocioDTO.urlFoto()));
        //AGREGUA LA UBICACION O SEA LOS DATOS X y Y
        negocio.getUbicacion().setLongitud(registroNegocioDTO.longitud());
        negocio.getUbicacion().setLatitud(registroNegocioDTO.latitud());

        // Asignar el cliente como propietario del negocio
        negocio.setCodigoCliente(registroNegocioDTO.codigoPropietario());

        negocio.setEstado(EstadoNegocio.PENDIENTE);


        // Guardar el negocio en la base de datos
        negocioRepo.save(negocio);

        return negocio.getCodigo(); // Devolver el ID del negocio creado
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {
        // Verificar si el usuario autenticado existe en la base de datos
        Optional<Cliente> optionalCliente = clienteRepo.findById(actualizarNegocioDTO.codigoCliente());
        if (optionalCliente.isEmpty()) {
            throw new Exception("El usuario autenticado no está registrado");
        }

        // Buscar el negocio por su ID
        Optional<Negocio> optionalNegocio = negocioRepo.findById(actualizarNegocioDTO.id());

        if (optionalNegocio.isEmpty()) {
            throw new Exception("El usuario no existe");
        }

        Negocio negocio = optionalNegocio.get();

        // Verificar si el usuario autenticado es propietario del negocio
        if (!negocio.getCodigoCliente().equals(actualizarNegocioDTO.codigoCliente())) {
            throw new Exception("El negocio no pertenece al usuario autenticado");
        }

        // Actualizar los campos del negocio con los valores proporcionados en el DTO
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setDescripcion(actualizarNegocioDTO.descripcion());

        negocio.setHorarios(actualizarNegocioDTO.horarios());
        negocio.setTelefonos(actualizarNegocioDTO.telefonos());
        negocio.setCategoriaNegocio(actualizarNegocioDTO.categoriaNegocio());
        negocio.setImagenes(Collections.singletonList(actualizarNegocioDTO.urlFoto()));
        negocio.setEstado(EstadoNegocio.PENDIENTE);

        // Guardar los cambios en la base de datos
        negocioRepo.save(negocio);

    }

    @Override
    public void eliminarNegocio(String idNegocio) throws Exception{
        Optional<Negocio> negocioOptional = negocioRepo.findById(idNegocio);
        if (negocioOptional.isEmpty()){
            throw new Exception("No existe un negocio con el codigo: "+ idNegocio);
        }
        Negocio negocio = negocioOptional.get();

        if (negocio.getEstado().equals(EstadoNegocio.ACTIVO)){
            negocio.setEstado(EstadoNegocio.INACTIVO);
        }
        else {
            throw new Exception(("No se que esta pasando"));
        }
    }

    @Override
    public Negocio buscarNegocio(String codigoNegocio) throws Exception{
        Optional<Negocio> negocioOptional = negocioRepo.findById(codigoNegocio);
        if(negocioOptional.isEmpty()){
            throw  new Exception("No existe negocio");
        }

        Negocio negocio = negocioOptional.get();
        return negocio;
    }

    @Override
    public List<Negocio> listarNegociosPropietario(String codigoPropietario) throws Exception{

        List<Negocio> optionalNegocios = negocioRepo.findAllByCodigoCliente(codigoPropietario);
        if(optionalNegocios.isEmpty()){
            throw new Exception("El usuario no tiene ningun negocio");
        }

        return optionalNegocios;
    }
@Override
    public List<Negocio> listarNegociosEstado(EstadoNegocio estado) throws Exception {

    List<Negocio> negocios = negocioRepo.findAllByEstadoNegocio(estado);

        if(negocios.isEmpty()){
            throw new Exception("No existen negocios con ese estado");
        }
        return negocios;
    }

    @Override
    public void eliminarNegocioRechazado() throws Exception {
        List<HistorialRevision> revisionOptional = historialRepo.findAllByEstadoAndFechaBefore(EstadoRevision.RECHAZADO,LocalDateTime.now().minusDays(5));
        if(revisionOptional.isEmpty()){
            throw new Exception("No hay negocios por eliminar");
        }
        for (HistorialRevision revision : revisionOptional) {
            Optional<Negocio> negocioOptional = negocioRepo.findById(revision.getCodigoNegocio());
            Negocio negocio = negocioOptional.get();
            negocio.setEstado(EstadoNegocio.INACTIVO);
            negocioRepo.save(negocio);
        }
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
