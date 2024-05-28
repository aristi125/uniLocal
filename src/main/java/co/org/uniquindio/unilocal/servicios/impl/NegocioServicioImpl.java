package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.*;
import co.org.uniquindio.unilocal.dto.Moderador.RevisionesModeradorDTO;
import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistroAgendaDTO;
import co.org.uniquindio.unilocal.dto.cliente.ItemListaLugaresCreadosDTO;
import co.org.uniquindio.unilocal.dto.negocio.*;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.modelo.documentos.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.entidades.Agenda;
import co.org.uniquindio.unilocal.modelo.entidades.Reserva;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.*;
import co.org.uniquindio.unilocal.repositorios.HistorialRevisionRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.EmailServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class NegocioServicioImpl implements NegocioServicio {

    private final ClienteServicio clienteServicio;
    private final NegocioRepo negocioRepo;
    private final EmailServicio emailServicio;
    private final HistorialRevisionRepo historialRepo;

    @Override
    public String crearNegocio(RegistroNegocioDTO registroNegocioDTO) throws Exception {
        // Verificar si el usuario autenticado existe en la base de datos
        clienteServicio.buscarCliente(registroNegocioDTO.codigoPropietario());

        // Crear un nuevo negocio con la información proporcionada en el DTO
        Negocio negocio = new Negocio();
        negocio.setNombre(registroNegocioDTO.nombre());
        negocio.setDescripcion(registroNegocioDTO.descripcion());
        negocio.setHorarios(registroNegocioDTO.horarios());
        negocio.setTelefonos((registroNegocioDTO.telefono()));
        negocio.setCategoriaNegocio(registroNegocioDTO.categoriaNegocio());
        negocio.setImagenes((registroNegocioDTO.urlFoto()));
        //AGREGUA LA UBICACION O SEA LOS DATOS X y Y
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLongitud(registroNegocioDTO.ubicacion().getLongitud());
        ubicacion.setLatitud(registroNegocioDTO.ubicacion().getLatitud());
        negocio.setUbicacion( ubicacion );

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
        clienteServicio.buscarCliente(actualizarNegocioDTO.codigoCliente());
        Negocio negocio = buscarNegocio(actualizarNegocioDTO.id());
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
    public void eliminarNegocio(IDClienteYNegocioDTO eliminacionNegocioDTO) throws Exception{
        clienteServicio.buscarCliente(eliminacionNegocioDTO.idCliente());
        Negocio negocio = buscarNegocio(eliminacionNegocioDTO.idNegocio());
        negocio.setEstado(EstadoNegocio.INACTIVO);
        negocioRepo.save(negocio);
    }

    @Override
    public ItemNegocioDTO verDetalleNegocio(String codigoNegocio) throws Exception{
        Negocio negocio = buscarNegocio(codigoNegocio);
        return new ItemNegocioDTO(
                negocio.getCodigo(),
                negocio.getNombre(),
                negocio.getImagenes().get(0),
                negocio.getCategoriaNegocio(),
                negocio.getUbicacion(),
                (long) negocio.getCalificaciones(), // acomodar para que se haga con el promedio
                negocio.getEstado()
        );
    }

    @Override
    public List<ItemNegocioDTO> listarNegocios() {

        List<Negocio> negocios = negocioRepo.findAll();
        List<Negocio> negociosaux = negocioRepo.findAll();
        List<ItemNegocioDTO> lugares = new ArrayList<>();

        for(Negocio n: negocios){
            if (n.getEstado()==EstadoNegocio.ACTIVO){
                negociosaux.add(n);
            }
        }

        for (Negocio n : negociosaux) {

                lugares.add(new ItemNegocioDTO(
                                n.getCodigo(),
                                n.getNombre(),
                                n.getImagenes().get(0),
                                n.getCategoriaNegocio(),
                                n.getUbicacion(), 0,
                                n.getEstado()
                        )
                );
        }
        return lugares;

    }

    @Override
    public DetalleNegocioDTO obtenerNegocio(String codigoNegocio) throws Exception {

        Negocio negocio = buscarNegocio(codigoNegocio);
        clienteServicio.buscarCliente(negocio.getCodigoCliente());
        return new DetalleNegocioDTO(
                negocio.getNombre(),
                negocio.getDescripcion(),
                negocio.getTelefonos(),
                negocio.getUbicacion(),
                negocio.getHorarios(),
                negocio.getCategoriaNegocio(),
                negocio.getImagenes(),
                negocio.getAgenda(),
                negocio.getCalificaciones()
        );
    }

    @Override
    public Negocio buscarNegocio(String codigoNegocio) throws Exception{
        Optional<Negocio> negocioOptional = negocioRepo.findById(codigoNegocio);
        if(negocioOptional.isEmpty()){
            throw  new Exception("No existe negocio");
        }

        Negocio negocio = negocioOptional.get();

        if(negocio.getEstado() == EstadoNegocio.INACTIVO){
            throw  new Exception("No existe negocio");
        }

        if( negocio.getHistorialRevisiones().isEmpty() ){
            throw new Exception("El negocio no esta aprobado");
        }

        int posicion = negocio.getHistorialRevisiones().size()-1;
        EstadoNegocioModerador estadoNegocio = negocio.getHistorialRevisiones().get(posicion).getEstado();
        if (estadoNegocio == EstadoNegocioModerador.PENDIENTE || estadoNegocio == EstadoNegocioModerador.RECHAZADO){
            throw new Exception("El negocio no esta aprobado");
        }
        return negocio;
    }

    @Override
    public List<Negocio> listarNegociosPropietario(String codigoPropietario) throws Exception{
        List<Negocio> negocios = negocioRepo.findAllByCodigoCliente(codigoPropietario);

        if(negocios.isEmpty()){
            throw new Exception("El usuario no tiene ningún negocio");
        }
        List<Negocio> negociosaux = new ArrayList<>();

        for(Negocio negocio: negocios){
            if(negocio.getEstado() == EstadoNegocio.ACTIVO){
                negociosaux.add(negocio);
            }
        }
        if(negociosaux.isEmpty()){
            throw new Exception("El usuario no tiene ningún negocio activos");
        }

        return negocios;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void eliminarNegocioRechazado() throws Exception {
        List<HistorialRevision> revisionOptional = historialRepo.findAllByEstadoAndFechaBefore(EstadoNegocioModerador.RECHAZADO,LocalDateTime.now().minusDays(5));
        if(revisionOptional.isEmpty()){
            throw new Exception("No hay negocios por eliminar");
        }
        for (HistorialRevision revision : revisionOptional) {
            Negocio negocio = buscarNegocio(revision.getCodigoNegocio());
            negocio.setEstado(EstadoNegocio.INACTIVO);
            negocioRepo.save(negocio);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void generarPDF(ReporteDTO reporteDTO) throws Exception {
        Cliente cliente = clienteServicio.buscarCliente(reporteDTO.codigoPropietario());
        Negocio negocio = buscarNegocio(reporteDTO.codigoNegocio());
        PDDocument document = null;
        final float LEADING = 21.5f;
        try {
            // Crear un nuevo documento PDF
            document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Inicia el flujo de contenido en la página
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                contentStream.newLineAtOffset(100, 700);
                // Agregar los datos del ReporteDTO al PDF
                contentStream.showText("Fecha: " + (LocalDate.now()));
                contentStream.newLineAtOffset(0, -LEADING);
                contentStream.showText("Código Propietario: " + cliente.getCodigo());
                contentStream.newLineAtOffset(0, -LEADING);
                contentStream.showText("Nombre Propietario: " + cliente.getNombre());
                contentStream.newLineAtOffset(0, -LEADING);
                contentStream.showText("Email Propietario: " + cliente.getEmail());
                contentStream.newLineAtOffset(0, -LEADING);
                contentStream.showText("Código de Negocio: " + negocio.getCodigo());
                contentStream.newLineAtOffset(0, -LEADING);
                contentStream.showText("Nombre del Negocio: " + negocio.getNombre());
                contentStream.newLineAtOffset(0, -LEADING);
                contentStream.showText("Número de Resevas: " + negocio.getListaReservas().size());
                contentStream.newLineAtOffset(0, -LEADING);
            }

            File file = File.createTempFile("Reporte", null);
            document.save(file);

            emailServicio.enviarCorreoArchivo(new EmailArchivoDTO(
                    "Reporte",
                    "Se ha generado un nuevo reporte para su negocio, " + "\n" +
                            "lo invitamos a verlo" + "\n",
                    cliente.getEmail(),
                    file
            ));

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

    @Override
    public List<ItemListaLugaresCreadosDTO> listarLugaresCreados(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception{

        clienteServicio.obtenerCliente(idClienteYNegocioDTO.idCliente());
        buscarNegocio(idClienteYNegocioDTO.idNegocio());

        List<Negocio> historialNegocio = negocioRepo.findAllByCodigoCliente(idClienteYNegocioDTO.idCliente());

        ArrayList<ItemListaLugaresCreadosDTO> respuesta = new ArrayList<>();

        if (historialNegocio.isEmpty()){
            throw new Exception("No ha creado ningun negocio");
        }

        for(Negocio n: historialNegocio){
            respuesta.add( new ItemListaLugaresCreadosDTO(
                            n.getCodigo(),
                            n.getNombre(),
                            n.getTelefonos(),
                            n.getCategoriaNegocio(),
                            n.getImagenes()
                    )
            );
        }
        return respuesta;
    }

    @Override
    public List<ItemListaLugaresCreadosDTO> buscarNegocioNombre(BusquedaNombreDTO busquedaNombreDTO) throws Exception {
        List<Negocio> negocios = negocioRepo.findAllByNombre(busquedaNombreDTO.nombre());
        List<ItemListaLugaresCreadosDTO> lugares = new ArrayList<>();
        if (negocios.isEmpty()) {
            throw new Exception("No se encontraron negocios con el nombre " + busquedaNombreDTO.nombre());
        }

        List<Negocio> negociosaux = new ArrayList<>();
        for(Negocio n: negocios){
            if (n.getEstado()==EstadoNegocio.ACTIVO){
                negociosaux.add(n);
            }
        }
        if (negociosaux.isEmpty()) {
            throw new Exception("No se encontraron negocios con el nombre " + busquedaNombreDTO.nombre() +" que se encuentren activos");
        }
        for (Negocio n : negociosaux) {

            if (n.getNombre().toLowerCase().contains(busquedaNombreDTO.nombre().toLowerCase()))
                lugares.add(new ItemListaLugaresCreadosDTO(
                                n.getCodigo(),
                                n.getNombre(),
                                n.getTelefonos(),
                                n.getCategoriaNegocio(),
                                n.getImagenes()
                        )
                );
        }
        return lugares;

    }

    @Override
    public List<ItemListaLugaresCreadosDTO> recomendarNegocio(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {
        buscarNegocio(idClienteYNegocioDTO.idNegocio());
        clienteServicio.obtenerCliente(idClienteYNegocioDTO.idCliente());
        //Cada que el usuario busque, guardar el término en una lista. con base en la lista recomendar negocios
        return List.of();
    }

    @Override
        public List<ItemListaLugaresCreadosDTO> buscarNegocioCategoria(CategoriaNegocio categoria) throws Exception {
        List<Negocio> negocios = negocioRepo.findAllByCategoriaNegocio(categoria);
        List<ItemListaLugaresCreadosDTO> lugares = new ArrayList<>();
        if (negocios.isEmpty()) {
            throw new Exception("No se encontraron negocios con la categoria " + categoria);
        }

        List<Negocio>  negociosaux = new ArrayList<>();
        for(Negocio negocio: negocios){
            if(negocio.getEstado()==EstadoNegocio.ACTIVO){
                negociosaux.add(negocio);
            }
        }
        if(negociosaux.isEmpty()){
            throw new Exception("No se encontraron negocios con la categoria " +categoria+" que se encuentra activos");
        }
        for (Negocio n : negociosaux) {
            lugares.add(new ItemListaLugaresCreadosDTO(
                            n.getCodigo(),
                            n.getNombre(),
                            n.getTelefonos(),
                            n.getCategoriaNegocio(),
                            n.getImagenes()
                    )
            );
        }
        return lugares;
    }

    @Override
    public List<ItemListaLugaresCreadosDTO> buscarNegocioDistancia(BusquedaDistanciaDTO busquedaDistanciaDTO) throws Exception {
        List<Negocio> negocios = negocioRepo.findByEstado(EstadoNegocio.ACTIVO);
        List<ItemListaLugaresCreadosDTO> lugaresCercanos = new ArrayList<>();

        for (Negocio negocio : negocios) {
            double distanciaCalculada = calcularDistancia(busquedaDistanciaDTO.ubicacion(), negocio.getUbicacion());

            if (distanciaCalculada <= busquedaDistanciaDTO.distancia()) {
                // Agregar el lugar a la lista de lugares dentro de la distancia especificada
                lugaresCercanos.add(new ItemListaLugaresCreadosDTO(
                        negocio.getCodigo(),
                        negocio.getNombre(),
                        negocio.getTelefonos(),
                        negocio.getCategoriaNegocio(),
                        negocio.getImagenes())
                );
            }
        }
        if(lugaresCercanos.isEmpty()){
            throw new Exception("No se encontraron lugares cercanos a la ubicacion" + busquedaDistanciaDTO.ubicacion());
        }

        return lugaresCercanos;

    }

    public double calcularDistancia(Ubicacion ubicacionCliente, Ubicacion ubicacionNegocio) {
        double radioTierra = 6371; //en kilómetros
        double dLat = Math.toRadians(ubicacionNegocio.getLatitud() - ubicacionCliente.getLatitud());
        double dLng = Math.toRadians(ubicacionNegocio.getLongitud() - ubicacionCliente.getLongitud());
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(ubicacionCliente.getLatitud())) * Math.cos(Math.toRadians(ubicacionNegocio.getLatitud()));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        return radioTierra * va2;
    }

    @Override
    public List<DetalleNegocioDTO> filtrarPorEstado(EstadoNegocioDTO estadoNegocioDTO)throws Exception {
        List<Negocio> negocios =negocioRepo.findByEstado(estadoNegocioDTO.estadoNegocio());
        if(negocios.isEmpty()){
            throw  new Exception("No existen negocios con ese estado");
        }

        List<DetalleNegocioDTO> lugares = new ArrayList<>();

        for (Negocio n : negocios) {
            lugares.add(new DetalleNegocioDTO(
                            n.getNombre(),
                            n.getDescripcion(),
                            n.getTelefonos(),
                            n.getUbicacion(),
                            n.getHorarios(),
                            n.getCategoriaNegocio(),
                            n.getImagenes(),
                            n.getAgenda(),
                            n.getCalificaciones()
                    )
            );
        }

        return lugares;
    }

    @Override
    public void registrarAgenda(RegistroAgendaDTO registrarAgendaDTO) throws Exception {
        clienteServicio.buscarCliente(registrarAgendaDTO.codigoCliente());
        Negocio negocio = buscarNegocio(registrarAgendaDTO.codigoNegocio());
        Agenda agendaNueva = new Agenda(
                registrarAgendaDTO.tematica(),
                registrarAgendaDTO.descripcion(),
                registrarAgendaDTO.estadoAgenda()
        );
        negocio.setAgenda(agendaNueva);
        negocioRepo.save(negocio);
    }

    @Override
    public void actualizarAgenda(RegistroAgendaDTO registrarAgendaDTO) throws Exception {
        clienteServicio.buscarCliente(registrarAgendaDTO.codigoCliente());
        Negocio negocio = buscarNegocio(registrarAgendaDTO.codigoNegocio());
        Agenda agenda = negocio.getAgenda();
        if (agenda.getTematica().equals(registrarAgendaDTO.tematica())) {
            throw new Exception("Ya existe una agenda con esa tematica");
        }
        Agenda agendaNueva = new Agenda(
                registrarAgendaDTO.tematica(),
                registrarAgendaDTO.descripcion(),
                registrarAgendaDTO.estadoAgenda()
        );
        negocio.setAgenda(agendaNueva);
        negocioRepo.save(negocio);
    }

    @Override
    public void eliminarAgenda(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {
        clienteServicio.buscarCliente(idClienteYNegocioDTO.idCliente());
        Negocio negocio = buscarNegocio(idClienteYNegocioDTO.idNegocio());
        Agenda agenda = negocio.getAgenda();
        agenda.setEstadoAgenda(EstadoAgenda.INACTIVA);
        negocioRepo.save(negocio);
    }

    @Override
    public DetalleAgendaDTO obtenerAgenda(String codigoNegocio) throws Exception {
        Negocio negocio = buscarNegocio(codigoNegocio);
        clienteServicio.buscarCliente(negocio.getCodigoCliente());
        Agenda agenda = negocio.getAgenda();
        return new DetalleAgendaDTO(
                agenda.getTematica(),
                agenda.getDescripcion());
    }

    @Override
    public void registrarReserva(DetalleReservaDTO detalleReservaDTO) throws Exception {

        Negocio negocio = buscarNegocio(detalleReservaDTO.codigoNegocio());
        clienteServicio.buscarCliente(negocio.getCodigoCliente());

        List<Reserva> listaReservas =  negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getFecha().equals(LocalDate.now()) && reserva.getHora().equals(LocalTime.now())) {
                throw new Exception("Ya existe una reserva para esa fecha y hora");
            }
        }

        Reserva reserva = new Reserva();
        reserva.setFecha(LocalDate.now());
        reserva.setHora(LocalTime.now());
        reserva.setCodigoNegocio(detalleReservaDTO.codigoNegocio());
        reserva.setCodigoCliente(detalleReservaDTO.codigoCliente());
        reserva.setCantidadPersonas(detalleReservaDTO.cantidadPersonas());

        negocio.getListaReservas().add(reserva);
        negocioRepo.save(negocio);
    }

    @Override
    public void actualizarReserva(DetalleReservaDTO detalleReservaDTO) throws Exception {

        Negocio negocio = buscarNegocio(detalleReservaDTO.codigoNegocio());
        clienteServicio.buscarCliente(negocio.getCodigoCliente());

        List<Reserva> listaReservas =  negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getCodigoNegocio().equals(detalleReservaDTO.codigoNegocio())) {
                LocalDate fechaActual = LocalDate.now();
                LocalTime horaActual = LocalTime.now();

                if (fechaActual.equals(reserva.getFecha())&& horaActual.equals(reserva.getHora())) {
                    throw new Exception("Ya existe una reserva con ese fecha");
                }
            }
        }

        List<Reserva> listaReservasNegocio = negocio.getListaReservas();
        for (Reserva reserva : listaReservasNegocio) {
            if (reserva.getCodigoCliente().equals(detalleReservaDTO.codigoCliente()) ){
                reserva.setFecha(LocalDate.now());
                reserva.setHora(LocalTime.now());
                reserva.setCantidadPersonas(detalleReservaDTO.cantidadPersonas());
                reserva.setCodigoCliente(detalleReservaDTO.codigoCliente());
                reserva.setCodigoNegocio(detalleReservaDTO.codigoNegocio());
                negocioRepo.save(negocio);
            }
        }
    }

    @Override
    public DetalleReservaDTO obtenerReserva(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {

        Negocio negocio = buscarNegocio(idClienteYNegocioDTO.idNegocio());
        clienteServicio.buscarCliente(idClienteYNegocioDTO.idCliente());

        List<Reserva> listaReservas = negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getCodigoCliente().equals(idClienteYNegocioDTO.idCliente())) {
                return new DetalleReservaDTO(reserva.getCodigoCliente(), reserva.getCodigoNegocio(), reserva.getCantidadPersonas()) ;

            }

        }
        throw new Exception("La reserva no existe");

    }

    @Override
    public void eliminarReserva(IDClienteYNegocioDTO idClienteYNegocioDTO) throws Exception {

        Cliente cliente = clienteServicio.buscarCliente(idClienteYNegocioDTO.idCliente());
        Negocio negocio = buscarNegocio(idClienteYNegocioDTO.idNegocio());

        List<Reserva> listaReservas = negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getCodigoCliente().equals(idClienteYNegocioDTO.idCliente())) {
                negocio.getListaReservas().remove(reserva);
            }
        }
        throw new Exception("La reserva no existe");

    }

    @Override
    public List<DetalleReservaDTO> listarReservas(String idNegocio) throws Exception{
        List<DetalleReservaDTO> respuesta = new ArrayList<>();
        Negocio negocio = buscarNegocio(idNegocio);
        clienteServicio.buscarCliente(negocio.getCodigoCliente());

        List<Reserva> listaReservas = negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            respuesta.add(new DetalleReservaDTO(reserva.getCodigoCliente(), reserva.getCodigoNegocio(), reserva.getCantidadPersonas()));
        }
        return respuesta;
    }

    @Override
    public List<HistorialRevision> obtenerHistorialRevisiones(String idNegocio) throws Exception{
        Negocio negocio = negocioRepo.findByCodigo(idNegocio);
        List<HistorialRevision> historiales = negocioRepo.findByCodigo(negocio.getCodigo()).getHistorialRevisiones();
        List<HistorialRevision> historialRevisionsaux = new ArrayList<HistorialRevision>();
        for(HistorialRevision historialRevision: historiales){
            historialRevision.setFecha(LocalDateTime.now());
            historialRevisionsaux.add(historialRevision);
        }

        if(historiales.isEmpty()){
            throw  new Exception("No existen historiales para este negocio");
        }
        return historiales;
    }


    @Override
    public void rechazarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        
        Negocio negocio = negocioRepo.findByCodigo(revisionesModeradorDTO.codigoNegocio());


        if(negocio.getEstado()==EstadoNegocio.ACTIVO||negocio.getEstado()==EstadoNegocio.INACTIVO){
            throw new Exception("El negocio no se encuentra en estado PENDIENTE");
        }
        HistorialRevision revision = new HistorialRevision();

        revision.setDescripcion(revisionesModeradorDTO.descripcion());
        revision.setCodigoModerador(revisionesModeradorDTO.codigoModerador());
        revision.setCodigoNegocio(revisionesModeradorDTO.codigoNegocio());
        revision.setEstado(EstadoNegocioModerador.RECHAZADO);

        historialRepo.save( revision );

        negocio.setEstado(EstadoNegocio.INACTIVO);
        negocio.getHistorialRevisiones().add(revision);

        Cliente cliente = clienteServicio.buscarCliente(negocio.getCodigoCliente());

        EmailDTO emailDTO = new EmailDTO(
                "Negocio Rechazado",
                "Hola " + cliente.getNombre() + "! \n\n" +
                        "Su negocio : "+negocio.getNombre()+"\n\n"+
                        "Identificado con: "+negocio.getCodigo()+"\n\n" +
                        "Fue rechazado debido a que no cumple con nuestras politicas" + "\n\n" +
                        "Por favor haz cambios y vuelve a hacer la solicitud antes de 5 dias habiles" + "\n\n" +
                        "Si se trata de un error, por favor comuníquese vía correo para solucionarlo. \n\n" +
                        "Gracias por confiar en nosotros!",
                cliente.getEmail()
        );
        emailServicio.enviarCorreo(emailDTO);
        negocioRepo.save(negocio);
    }

    @Override
    public void aprobarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {

        Negocio negocio = negocioRepo.findByCodigo(revisionesModeradorDTO.codigoNegocio());

        if(negocio.getEstado()==EstadoNegocio.ACTIVO || negocio.getEstado()==EstadoNegocio.INACTIVO){
            throw new Exception("El negocio no se encuentra en estado PENDIENTE");
        }
        HistorialRevision revision = new HistorialRevision();

        revision.setDescripcion(revisionesModeradorDTO.descripcion());
        revision.setCodigoModerador(revisionesModeradorDTO.codigoModerador());
        revision.setCodigoNegocio(revisionesModeradorDTO.codigoNegocio());
        revision.setEstado(EstadoNegocioModerador.APROBADO);

        historialRepo.save( revision );

        negocio.setEstado(EstadoNegocio.ACTIVO);
        negocio.getHistorialRevisiones().add(revision);

        Cliente cliente = clienteServicio.buscarCliente(negocio.getCodigoCliente());

        EmailDTO emailDTO = new EmailDTO(
                "Negocio Aceptado",
                "Hola " + cliente.getNombre() + "! \n\n" +
                        "Su negocio : "+negocio.getNombre()+"\n\n"+
                        "Identificado con: "+negocio.getCodigo()+"\n\n" +
                        "Fue aceptado debido a que cumple con nuestras politicas" + "\n\n" +
                        "Felicidades!" + "\n\n" +
                        "Si se trata de un error, porfavor comuniquese via correo para solucionarlo. \n\n" +
                        "Gracias por confiar en nosotros!",
                cliente.getEmail()
        );
        emailServicio.enviarCorreo(emailDTO);
        negocioRepo.save(negocio);
    }


    @Override
    public Cliente obtenerClienteNegocio (String idNegocio) throws Exception {
        Negocio negocio = buscarNegocio(idNegocio);
        return clienteServicio.buscarCliente(negocio.getCodigoCliente());
    }

    public static double calcularPromedio(List<Integer> calificaciones) {
        if (calificaciones == null || calificaciones.isEmpty()) {
            return 0;
        }
        int suma = 0;
        for (int calificacion : calificaciones) {
            suma += calificacion;
        }
        return (double) suma / calificaciones.size();
    }
}
