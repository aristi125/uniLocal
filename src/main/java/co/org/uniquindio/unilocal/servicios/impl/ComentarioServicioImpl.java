package co.org.uniquindio.unilocal.servicios.impl;


import co.org.uniquindio.unilocal.dto.comentario.ComentarioDTO;
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



@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ClienteRepo clienteRepo;
    private final NegocioRepo negocioRepo;
    private final ComentarioRepo comentarioRepo;


    @Override
    public void crearComentario(ComentarioDTO comentario) {

    }

    @Override
    public void responderComentario() {

    }

    @Override
    public List<ItemListaComentariosDTO> listarComentariosNegocio(QuienHizoComentarioDTO hizoComentarioDTO) throws Exception {
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
                    c.getCodigoComemtario(),
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