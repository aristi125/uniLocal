package co.org.uniquindio.unilocal.servicios.impl;


import co.org.uniquindio.unilocal.dto.comentario.*;
import co.org.uniquindio.unilocal.modelo.documentos.Comentario;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.repositorios.ClienteRepo;
import co.org.uniquindio.unilocal.repositorios.ComentarioRepo;
import co.org.uniquindio.unilocal.repositorios.NegocioRepo;
import co.org.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;
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
    private final NegocioRepo negocioRepo;
    private final ComentarioRepo comentarioRepo;

    @Override
    public void crearComentario(RegistroComentarioDTO comentario) {
        Negocio negocio = negocioRepo.findByCodigo(comentario.codigoNegocio());
        List<Comentario> listaComentarios = comentarioRepo.findAllByCodigoNegocio(negocio.getCodigo());
        Comentario comentarioComentario = new Comentario();
        comentarioComentario.setFecha(comentario.fecha());
        comentarioComentario.setCalificacion(comentario.calificacion());
        comentarioComentario.setCodigoCliente(comentario.codigoCliente());
        comentarioComentario.setCodigoNegocio(negocio.getCodigo());
        comentarioComentario.setMensaje(comentario.mensaje());
        comentarioComentario.setRespuesta("");

        listaComentarios.add(comentarioComentario);
        negocio.setComentarios(listaComentarios);
        negocioRepo.save(negocio);
        comentarioRepo.save(comentarioComentario);
    }

    @Override
    public void responderComentario(RespuestaComentarioDTO comentario) {
        Negocio negocio = negocioRepo.findByCodigo(comentario.codigoNegocio());
        Comentario aux = comentarioRepo.findByCodigoComentario(comentario.codigoComentario());
        aux.setRespuesta(comentario.respuesta());
        comentarioRepo.save(aux);
    }

    @Override
    public List<ItemListaComentariosDTO> listarComentariosNegocio(String codigoNegocio) throws Exception {

        Optional<Negocio> negocio = negocioRepo.findById(codigoNegocio);
        if (negocio.isEmpty()) {
            throw new Exception("No existe negocio");
        }
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

        Optional<Negocio> negocio = negocioRepo.findById(codigoNegocio);
        if (negocio.isEmpty()) {
            throw new Exception("No existe negocio");
        }

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