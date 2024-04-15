package co.org.uniquindio.unilocal.servicios.impl;


import co.org.uniquindio.unilocal.dto.comentario.ComentarioDTO;
import co.org.uniquindio.unilocal.dto.comentario.DetalleComentarioDTO;
import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.dto.comentario.QuienHizoComentarioDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
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
import co.org.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;

@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ClienteRepo clienteRepo;
    private final NegocioRepo negocioRepo;
    private final ComentarioRepo comentarioRepo;


    @Override
    public void crearComentario(ComentarioDTO comentario) {
        Negocio negocio = negocioRepo.findByCodigo(comentario.codigoNegocio());
        List<Comentario> listaComentarios = comentarioRepo.findAllByCodigoNegocio(negocio.getCodigo());
        Comentario comentarioComentario = new Comentario();
        comentarioComentario.setFecha(comentario.horaComentario());
        comentarioComentario.setMensaje(comentario.mensaje());
        comentarioComentario.setRespuesta("");
        comentarioComentario.setCodigoNegocio(negocio.getCodigo());

        listaComentarios.add(comentarioComentario);
        negocio.setComentarios(listaComentarios);
        negocioRepo.save(negocio);
        comentarioRepo.save(comentarioComentario);
    }

    @Override
    public void responderComentario(ComentarioDTO comentario) {
        Negocio negocio = negocioRepo.findByCodigo(comentario.codigoNegocio());
        Comentario aux = comentarioRepo.findByCodigoComentario(comentario.codigoComentario());
        aux.setRespuesta(comentario.respuesta());
        comentarioRepo.save(aux);
    }

    @Override
    public List<ItemListaComentariosDTO> listarComentariosNegocio(DetalleComentarioDTO detalleComentarioDTO, QuienHizoComentarioDTO hizoComentarioDTO) throws Exception {
        Optional<Cliente> cliente = clienteRepo.findById(hizoComentarioDTO.idCliente());

        if (cliente.isEmpty()) {
            throw new Exception("No exsite cliente");
        }
        Optional<Negocio> negocio = negocioRepo.findById(hizoComentarioDTO.idNegocio());
        if (negocio.isEmpty()) {
            throw new Exception("No exsite negocio");
        }
        List<Comentario> historialComentario = comentarioRepo.findAllByCodigoNegocio(hizoComentarioDTO.idNegocio());
        List<ItemListaComentariosDTO> respuesta = new ArrayList<>();
        if (historialComentario.isEmpty()) {
            throw new Exception("No hay comentarios");
        }

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
    public void calcularPromedioCalificaciones () {

    }
}