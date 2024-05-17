package co.org.uniquindio.unilocal.servicios.impl;

import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.dto.Moderador.RevisionesModeradorDTO;
import co.org.uniquindio.unilocal.dto.agenda.DetalleAgendaDTO;
import co.org.uniquindio.unilocal.dto.agenda.RegistroAgendaDTO;
import co.org.uniquindio.unilocal.dto.cliente.FavoritoDTO;
import co.org.uniquindio.unilocal.dto.cliente.ItemListaLugaresCreadosDTO;
import co.org.uniquindio.unilocal.dto.negocio.*;
import co.org.uniquindio.unilocal.dto.reserva.DetalleReservaDTO;
import co.org.uniquindio.unilocal.modelo.documentos.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.documentos.Moderador;
import co.org.uniquindio.unilocal.modelo.entidades.Agenda;
import co.org.uniquindio.unilocal.modelo.entidades.Reserva;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocioModerador;
import co.org.uniquindio.unilocal.repositorios.HistorialRevisionRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.EmailServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ModeradorServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NegocioServicioImpl implements NegocioServicio {

    private final ClienteServicio clienteServicio;
    private final ModeradorServicio moderadorServicio;
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
        clienteServicio.obtenerCliente(actualizarNegocioDTO.codigoCliente());
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
    public void eliminarNegocio(EliminacionNegocioDTO eliminacionNegocioDTO) throws Exception{
        clienteServicio.buscarCliente(eliminacionNegocioDTO.idCliente());
        buscarNegocio(eliminacionNegocioDTO.idNegocio());

        Negocio negocio = new Negocio();

        if (negocio.getEstado().equals(EstadoNegocio.ACTIVO)){
            negocio.setEstado(EstadoNegocio.INACTIVO);
        }
        else {
            throw new Exception(("No se que esta pasando"));
        }
        negocioRepo.save(negocio);
    }

    @Override
    public DetalleNegocioDTO obtenerNegocio(String codigoNegocio) throws Exception {
        Negocio negocio = buscarNegocio(codigoNegocio);

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
        //El negocio debe estar aprobado, recorrer la lista del historial de revisiones y si el último registro No es Aprobado lanzar excepción

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

    List<Negocio> negocios = negocioRepo.findAllByEstado(estado);

        if(negocios.isEmpty()){
            throw new Exception("No existen negocios con ese estado");
        }
        return negocios;
    }

    @Override
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


    @Override
    public void generarPDF(ReporteDTO reporteDTO, String rutaArchivo) throws Exception {
        Cliente cliente = clienteServicio.buscarCliente(reporteDTO.codigoPropietario());
        buscarNegocio(reporteDTO.codigoNegocio());

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
                contentStream.showText("Fecha: " + (LocalDateTime.now()));
                contentStream.newLine();
                contentStream.showText("Código Propietario: " + cliente.getCodigo());
                contentStream.newLine();
                contentStream.showText("Nombre Propietario: " + cliente.getNombre());
                contentStream.newLine();
                contentStream.showText("Email Propietario: " + cliente.getEmail());
                contentStream.newLine();
                contentStream.showText("Código de Negocio: " + reporteDTO.codigoNegocio());
                contentStream.newLine();
                contentStream.showText("Nombre del Negocio: " + reporteDTO.nombreNegocio());
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

    @Override
    public List<ItemListaLugaresCreadosDTO> listaLugaresCreados(String idCliente, String idNegocio) throws Exception{

        clienteServicio.obtenerCliente(idCliente);
        Negocio negocio = buscarNegocio(idNegocio);

        List<Negocio> historialNegocio = negocioRepo.findAllByCodigoCliente(idCliente);

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
    public List<ItemListaLugaresCreadosDTO> buscarNegocioNombre(String nombre) throws Exception {

        List<Negocio> negocios = negocioRepo.findAllByNombre(nombre);
        List<ItemListaLugaresCreadosDTO> lugares = new ArrayList<>();
        if (negocios.isEmpty()) {
            throw new Exception("No se encontraron negocios con el nombre " + nombre);
        }
        for (Negocio n : negocios) {

            if (n.getNombre().toLowerCase().contains(nombre.toLowerCase()))
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
    public List<ItemListaLugaresCreadosDTO> recomendarNegocio(String busqueda) throws Exception {
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
        for (Negocio n : negocios) {
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
    public List<ItemListaLugaresCreadosDTO> buscarNegocioDistancia(double distancia, Ubicacion ubicacionCliente) throws Exception {

        List<Negocio> negocios = negocioRepo.findAll();
        List<ItemListaLugaresCreadosDTO> lugaresCercanos = new ArrayList<>();

        for (Negocio negocio : negocios) {
            double distanciaCalculada = calcularDistancia(ubicacionCliente, negocio.getUbicacion());

            if (distanciaCalculada <= distancia) {
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
    public List<ItemListaLugaresCreadosDTO> filtrarPorEstado(EstadoNegocio estadoNegocio)throws Exception {
        List<Negocio> negocios =negocioRepo.findByEstado(estadoNegocio);
        if(negocios.isEmpty()){
            throw  new Exception("No existen negocios con ese estado");
        }

        List<ItemListaLugaresCreadosDTO> lugares = new ArrayList<>();

        for (Negocio n : negocios) {
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
    public void registrarAgenda(RegistroAgendaDTO registrarAgendaDTO) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepo.findById(registrarAgendaDTO.codigoNegocio());
        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no existe");
        }
        Negocio negocio = negocioOptional.get();
        Agenda agendaNueva = new Agenda(
                registrarAgendaDTO.tematica(),
                registrarAgendaDTO.descripcion()
        );
        negocio.setAgenda(agendaNueva);
        negocioRepo.save(negocio);
    }

    @Override
    public void actualizarAgenda(RegistroAgendaDTO registrarAgendaDTO) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepo.findById(registrarAgendaDTO.codigoNegocio());
        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no existe");
        }
        Negocio negocio = negocioOptional.get();
        Agenda agenda = negocio.getAgenda();
        if (agenda.getTematica().equals(registrarAgendaDTO.tematica())) {
            throw new Exception("Ya existe una agenda con esa tematica");
        }
        Agenda agendaNueva = new Agenda(
                registrarAgendaDTO.tematica(),
                registrarAgendaDTO.descripcion()
        );
        negocio.setAgenda(agendaNueva);
        negocioRepo.save(negocio);
    }

    @Override
    public void eliminarAgenda(String codigoNegocio) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepo.findById(codigoNegocio);
        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no existe");
        }
        Negocio negocio = negocioOptional.get();
        Agenda agendaNueva = new Agenda(
                " ",
                " "
        );
        negocio.setAgenda(agendaNueva);
        negocioRepo.save(negocio);
    }

    @Override
    public DetalleAgendaDTO obtenerAgenda(String codigoNegocio) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepo.findById(codigoNegocio);
        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no existe");
        }
        Negocio negocio = negocioOptional.get();
        Agenda agenda = negocio.getAgenda();
        return new DetalleAgendaDTO(
                agenda.getTematica(),
                agenda.getDescripcion());
    }


    @Override
    public void agregarFavoritos(String idNegocio, String idCliente) throws Exception{

        Negocio negocio = buscarNegocio(idNegocio);
        Cliente cliente = clienteServicio.buscarCliente(idCliente);

        if (cliente.getAgregarFavoritos() == null) {
            cliente.setAgregarFavoritos(new ArrayList<>());
        }

        cliente.getAgregarFavoritos().add(negocio);
    }

    @Override
    public List<FavoritoDTO> mostrarFavoritos(String idCliente) throws Exception{

        Cliente cliente = clienteServicio.buscarCliente(idCliente);

        List<Negocio> favoritoCliente = cliente.getAgregarFavoritos();
        List<FavoritoDTO> favoritos = new ArrayList<>();
        Negocio negocio = null;
        FavoritoDTO favoritoDTO = null;
        for (Negocio favorito : favoritoCliente) {
            negocio = buscarNegocio(favorito.getCodigo());
            favoritoDTO = new FavoritoDTO(
                    negocio.getCodigo(),
                    negocio.getImagenes().get(0),
                    negocio.getNombre(),
                    negocio.getUbicacion()
            );
            favoritos.add(favoritoDTO);
        }
        return favoritos;
    }

    @Override
    public void removerFavoritos(String idNegocio, String idCliente) throws Exception{
        Negocio negocio = buscarNegocio(idNegocio);
        Cliente cliente = clienteServicio.buscarCliente(idCliente);
        cliente.getAgregarFavoritos().remove(negocio);
    }


    @Override
    public void registrarReserva(DetalleReservaDTO detalleReservaDTO) throws Exception {

        Cliente cliente = clienteServicio.buscarCliente(detalleReservaDTO.codigoCliente());
        Negocio negocio = buscarNegocio(detalleReservaDTO.codigoNegocio());

        List<Reserva> listaReservas =  negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getFecha().equals(detalleReservaDTO.fecha()) && reserva.getHora().equals(detalleReservaDTO.hora())) {
                throw new Exception("Ya existe una reserva para esa fecha y hora");
            }
        }

        Reserva reserva = new Reserva();
        reserva.setFecha(detalleReservaDTO.fecha());
        reserva.setHora(detalleReservaDTO.hora());
        reserva.setCantidadPersonas(detalleReservaDTO.cantidadPersonas());
        reserva.setCodigoCliente(detalleReservaDTO.codigoCliente());
        reserva.setCodigoNegocio(detalleReservaDTO.codigoNegocio());

        negocio.getListaReservas().add(reserva);
        negocioRepo.save(negocio);
    }

    @Override
    public void actualizarReserva(DetalleReservaDTO detalleReservaDTO) throws Exception {

        Cliente cliente = clienteServicio.buscarCliente(detalleReservaDTO.codigoCliente());
        Negocio negocio = buscarNegocio(detalleReservaDTO.codigoNegocio());

        List<Reserva> listaReservas =  negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getFecha().equals(detalleReservaDTO.fecha()) && reserva.getHora().equals(detalleReservaDTO.hora())) {
                throw new Exception("Ya existe una reserva para esa fecha y hora");
            }
        }

        List<Reserva> listaReservasNegocio = negocio.getListaReservas();
        for (Reserva reserva : listaReservasNegocio) {
            if (reserva.getCodigoCliente().equals(detalleReservaDTO.codigoCliente()) ){
                reserva.setFecha(detalleReservaDTO.fecha());
                reserva.setHora(detalleReservaDTO.hora());
                reserva.setCantidadPersonas(detalleReservaDTO.cantidadPersonas());
                reserva.setCodigoCliente(detalleReservaDTO.codigoCliente());
                reserva.setCodigoNegocio(detalleReservaDTO.codigoNegocio());
                negocioRepo.save(negocio);
                return;
            }
        }
    }

    @Override
    public DetalleReservaDTO obtenerReserva(String idNegocio, String idCliente) throws Exception {

        Cliente cliente = clienteServicio.buscarCliente(idCliente);
        Negocio negocio = buscarNegocio(idNegocio);

        List<Reserva> listaReservas = negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getCodigoCliente().equals(idCliente)) {
                return new DetalleReservaDTO(reserva.getFecha(), reserva.getHora(), reserva.getCantidadPersonas(), reserva.getCodigoCliente(), reserva.getCodigoNegocio()) ;

            }

        }
        throw new Exception("La reserva no existe");

    }

    @Override
    public void eliminarReserva(String idNegocio, String idCliente) throws Exception {

        Cliente cliente = clienteServicio.buscarCliente(idCliente);
        Negocio negocio = buscarNegocio(idNegocio);

        List<Reserva> listaReservas = negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            if (reserva.getCodigoCliente().equals(idCliente)) {
                negocio.getListaReservas().remove(reserva);
                negocioRepo.save(negocio);
                return;
            }
        }
        throw new Exception("La reserva no existe");

    }

    @Override
    public List<DetalleReservaDTO> listarReservas(String idNegocio) throws Exception{
        List<DetalleReservaDTO> respuesta = new ArrayList<>();
        Negocio negocio = buscarNegocio(idNegocio);

        List<Reserva> listaReservas = negocio.getListaReservas();

        for (Reserva reserva : listaReservas) {
            respuesta.add(new DetalleReservaDTO(reserva.getFecha(), reserva.getHora(), reserva.getCantidadPersonas(), reserva.getCodigoCliente(), reserva.getCodigoNegocio()));
        }
        return respuesta;
    }

    @Override
    public List<HistorialRevision> obtenerHistorialRevisiones(String idNegocio) throws Exception{
        buscarNegocio(idNegocio);
        List<HistorialRevision> historiales = negocioRepo.findByCodigo(idNegocio).getHistorialRevisiones();
        if(historiales.isEmpty()){
            throw  new Exception("No existen historiales para este negocio");
        }
        return historiales;
    }


    @Override
    public void rechazarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        Negocio negocio = buscarNegocio(revisionesModeradorDTO.codigoNegocio());

        HistorialRevision revision = new HistorialRevision();

        revision.setDescripcion(revisionesModeradorDTO.descripcion());
        revision.setCodigoModerador(revisionesModeradorDTO.codigoModerador());
        revision.setCodigoNegocio(revisionesModeradorDTO.codigoNegocio());
        revision.setEstado(EstadoNegocioModerador.RECHAZADO);


        negocio.setEstado(EstadoNegocio.INACTIVO);
        negocio.getHistorialRevisiones().add(revision);

        Cliente cliente = obtenerClienteNegocio(negocio.getCodigo());

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

        Negocio negocio = buscarNegocio(revisionesModeradorDTO.codigoNegocio());

        HistorialRevision revision = new HistorialRevision();

        revision.setDescripcion(revisionesModeradorDTO.descripcion());
        revision.setCodigoModerador(revisionesModeradorDTO.codigoModerador());
        revision.setCodigoNegocio(revisionesModeradorDTO.codigoNegocio());
        revision.setEstado(EstadoNegocioModerador.APROBADO);


        negocio.setEstado(EstadoNegocio.ACTIVO);
        negocio.getHistorialRevisiones().add(revision);

        Cliente cliente = obtenerClienteNegocio(negocio.getCodigo());

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
}
