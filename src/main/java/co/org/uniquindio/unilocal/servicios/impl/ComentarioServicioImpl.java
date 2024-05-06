package co.org.uniquindio.unilocal.servicios.impl;


import co.org.uniquindio.unilocal.dto.EmailDTO;
import co.org.uniquindio.unilocal.dto.comentario.*;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Comentario;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.ComentarioRepo;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.EmailServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ClienteRepo clienteRepo; // Validar que el cliente exista
    private final NegocioServicio negocioServicio;
    private final ComentarioRepo comentarioRepo;
    private final EmailServicio emailServicio;

    @Override
    public void crearComentario(RegistroComentarioDTO comentario) throws Exception {

        Negocio negocio = negocioServicio.buscarNegocio(comentario.codigoNegocio());

        Optional<Cliente> clienteOptional = clienteRepo.findById(comentario.codigoCliente());
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("No existe cliente");
        }

        List<Comentario> listaComentarios = comentarioRepo.findAllByCodigoNegocio(comentario.codigoNegocio());
        Comentario comentarioComentario = new Comentario();
        comentarioComentario.setFecha(comentario.fecha());
        comentarioComentario.setCalificacion(comentario.calificacion());
        comentarioComentario.setCodigoCliente(comentario.codigoCliente());
        comentarioComentario.setCodigoNegocio(comentario.codigoNegocio()); //Este código relaciona todos los comentarios con un negocio
        comentarioComentario.setMensaje(comentario.mensaje());
        comentarioComentario.setRespuesta("");

        comentarioRepo.save(comentarioComentario);

        String codigoPropietario = negocio.getCodigoCliente();
        Optional<Cliente> propietarioOptional = clienteRepo.findById(codigoPropietario);
        if (propietarioOptional.isEmpty()) {
            throw new RuntimeException("No existe propietario");
        }
        Cliente propietario = propietarioOptional.get();
        String email = propietario.getEmail();

        // Enviar correo al propietario
        EmailDTO emailDTO = new EmailDTO(
                "Nuevo comentario",
                "Hola " + propietario.getNombre() + "! \n\n" +
                        "Tu negocio " + negocio.getNombre() + " ha recibido un nuevo comentario. \n\n" +
                        comentario.mensaje() + "\n\n" +
                        "Ingresa a la plataforma para responderlo. \n\n" +
                        "Gracias por confiar en nosotros!",
                email
        );

        emailServicio.enviarCorreo(emailDTO);



    }

    @Override
    public void responderComentario(RespuestaComentarioDTO comentario) throws Exception{
        Optional<Cliente> clienteOptional = clienteRepo.findById(comentario.codigoClienteReceptor());
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("No existe cliente receptor");
        }

        Negocio negocio = negocioServicio.buscarNegocio(comentario.codigoNegocio());

        List<Comentario> listaComentarios = negocio.getComentarios();
        for (Comentario c : listaComentarios) {
            if (c.getCodigoComentario().equals(comentario.codigoComentario())) {
                c.setRespuesta(comentario.respuesta());
                break;
            }
        }

        Comentario aux = comentarioRepo.findByCodigoComentario(comentario.codigoComentario());
        aux.setRespuesta(comentario.respuesta());
        comentarioRepo.save(aux);

        Cliente cliente = clienteOptional.get();
        String email = cliente.getEmail();
        // Enviar correo al cliente

        EmailDTO emailDTO = new EmailDTO(
                "Respuesta a tu comentario",
                "Hola " + cliente.getNombre() + "! \n\n" +
                        "Tu comentario ha sido respondido. \n\n" +
                        comentario.respuesta() + "\n\n" +
                        "Gracias por confiar en nosotros!",
                email
        );

        emailServicio.enviarCorreo(emailDTO);

    }

    @Override
    public List<ItemListaComentariosDTO> listarComentariosNegocio(String codigoNegocio) throws Exception {

        Negocio negocio = negocioServicio.buscarNegocio(codigoNegocio);

        List<Comentario> historialComentario = comentarioRepo.findAllByCodigoNegocio(codigoNegocio);
        if (historialComentario.isEmpty()) {
            throw new Exception("No hay comentarios");
        }

        List<ItemListaComentariosDTO> respuesta = new ArrayList<>();
        for (Comentario c : historialComentario) {
            respuesta.add(new ItemListaComentariosDTO(
                    c.getCodigoComentario(),
                    c.getFecha(),
                    c.getMensaje()
            ));
        }
        return respuesta;
    }

    @Override
    public int calcularPromedioCalificaciones (String codigoNegocio) throws Exception {

        Negocio negocio = negocioServicio.buscarNegocio(codigoNegocio);

        List<Comentario> listaComentarios = comentarioRepo.findAllByCodigoNegocio(codigoNegocio);
        if (listaComentarios.isEmpty()) {
            throw new Exception("No hay comentarios");
        }

        int suma = 0;
        for (Comentario c : listaComentarios) {
            suma += c.getCalificacion();
        }
        return suma / listaComentarios.size();

    }
}