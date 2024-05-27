package co.org.uniquindio.unilocal.servicios.impl;


import co.org.uniquindio.unilocal.dto.CategoriaNegocioDTO;
import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.dto.cliente.ItemListaLugaresCreadosDTO;
import co.org.uniquindio.unilocal.dto.comentario.*;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Comentario;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.repositorios.ComentarioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.EmailServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final NegocioServicio negocioServicio;
    private final ComentarioRepo comentarioRepo; // Repositorio de la propia clase
    private final EmailServicio emailServicio;
    private final ClienteServicio clienteServicio;

    @Override
    public void crearComentario(RegistroComentarioDTO registroComentarioDTO) throws Exception {
        Cliente propietario = clienteServicio.buscarCliente(registroComentarioDTO.codigoCliente());
        Negocio negocio = negocioServicio.buscarNegocio(registroComentarioDTO.codigoNegocio());

        if(registroComentarioDTO.calificacion()<0 || registroComentarioDTO.calificacion()>5){
            throw new Exception("Calificacion excede el rango");
        }
        Comentario comentario = new Comentario();
        comentario.setFecha(LocalDateTime.now());
        comentario.setCalificacion(registroComentarioDTO.calificacion());
        comentario.setCodigoCliente(registroComentarioDTO.codigoCliente());
        comentario.setCodigoNegocio(registroComentarioDTO.codigoNegocio()); //Este código relaciona todos los comentarios con un negocio
        comentario.setMensaje(registroComentarioDTO.mensaje());

        comentarioRepo.save(comentario);

        // Enviar correo al propietario
        EmailDTO emailDTO = new EmailDTO(
                "Nuevo comentario",
                "Hola " + propietario.getNombre() + "! \n\n" +
                        "Tu negocio " + negocio.getNombre() + " ha recibido un nuevo comentario. \n\n" +
                        registroComentarioDTO.mensaje() + "\n\n" +
                        "Ingresa a la plataforma para responderlo. \n\n" +
                        "Gracias por confiar en nosotros!",
                propietario.getEmail()
        );
        emailServicio.enviarCorreo(emailDTO);
        calcularPromedioCalificaciones(registroComentarioDTO.codigoNegocio());
    }

    @Override
    public void responderComentario(RespuestaComentarioDTO respuestaComentarioDTO) throws Exception{

        Cliente cliente = clienteServicio.buscarCliente(respuestaComentarioDTO.codigoClienteReceptor());
        Negocio negocio = negocioServicio.buscarNegocio(respuestaComentarioDTO.codigoNegocio());

        List<Comentario> listaComentarios = negocio.getComentarios();
        for (Comentario c : listaComentarios) {
            if (c.getCodigoComentario().equals(respuestaComentarioDTO.codigoComentario())) {
                c.setRespuesta(respuestaComentarioDTO.respuesta());
                break;
            }
        }

        Comentario aux = comentarioRepo.findByCodigoComentario(respuestaComentarioDTO.codigoComentario());
        aux.setRespuesta(respuestaComentarioDTO.respuesta());
        comentarioRepo.save(aux);

        EmailDTO emailDTO = new EmailDTO(
                "Respuesta a tu comentario",
                "Hola " + cliente.getNombre() + "! \n\n" +
                        "Tu comentario ha sido respondido. \n\n" +
                        respuestaComentarioDTO.respuesta() + "\n\n" +
                        "Gracias por confiar en nosotros!",
                cliente.getEmail()
        );

        emailServicio.enviarCorreo(emailDTO);

    }

    @Override
    public List<ItemListaComentariosDTO> listarComentariosNegocio(String codigoNegocio) throws Exception {

        Negocio negocio = negocioServicio.buscarNegocio(codigoNegocio);
        clienteServicio.buscarCliente(negocio.getCodigoCliente());
        List<Comentario> historialComentario = comentarioRepo.findAllByCodigoNegocio(codigoNegocio);
        if (historialComentario.isEmpty()) {
            throw new Exception("No hay comentarios");
        }

        List<ItemListaComentariosDTO> respuesta = new ArrayList<>();
        for (Comentario c : historialComentario) {
            respuesta.add(new ItemListaComentariosDTO(
                    c.getCodigoComentario(),
                    c.getMensaje()
            ));
        }
        return respuesta;
    }

    @Override
    public void calcularPromedioCalificaciones (String codigoNegocio) throws Exception {
        negocioServicio.buscarNegocio(codigoNegocio);
        List<Comentario> listaComentarios = comentarioRepo.findAllByCodigoNegocio(codigoNegocio);
        if (listaComentarios.isEmpty()) {
            throw new Exception("No hay comentarios");
        }
        int suma = 0;
        for (Comentario c : listaComentarios) {
            suma += c.getCalificacion();
        }
    }

    @Override
    public List<ItemListaComentariosDTO> listarComentariosTipoNegocio(CategoriaNegocio categoria) throws Exception {

        CategoriaNegocioDTO categoriaNegocioDTO = new CategoriaNegocioDTO(categoria);
            List<ItemListaLugaresCreadosDTO> listaNegocios = negocioServicio.buscarNegocioCategoria(categoriaNegocioDTO);
            if (listaNegocios.isEmpty()) {
                throw new Exception("No hay negocios con esta categoria");
            }

            List<ItemListaComentariosDTO> listaComentarios = new ArrayList<>();
            for (ItemListaLugaresCreadosDTO negocio : listaNegocios) {
                List<Comentario> comentarios = comentarioRepo.findAllByCodigoNegocio(negocio.idNegocio());
                for (Comentario c : comentarios) {
                    listaComentarios.add(new ItemListaComentariosDTO(
                            c.getCodigoComentario(),
                            c.getMensaje()
                    ));
                }

            }
            return listaComentarios;
    }
}