package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.dto.comentario.RegistroComentarioDTO;
import co.org.uniquindio.unilocal.dto.comentario.RespuestaComentarioDTO;
import co.org.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/crear-comentario")
    public ResponseEntity<MensajeDTO<String>> crearComentario(@Valid @RequestBody RegistroComentarioDTO registroComentarioDTO) throws Exception {
        comentarioServicio.crearComentario(registroComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario creado exitosamente"));
    }

    @PostMapping("/responder-comentario")
    public ResponseEntity<MensajeDTO<String>> responderComentario(@Valid @RequestBody RespuestaComentarioDTO respuestaComentarioDTO) {
        comentarioServicio.responderComentario(respuestaComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "El comentario ha sido respondido de manera exitosa"));
    }

    @GetMapping("/listar-comentarios-negocio")
    public ResponseEntity<MensajeDTO<List<ItemListaComentariosDTO>>> listarComentariosNegocio(@PathVariable String idNegocio) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.listarComentariosNegocio(idNegocio)));
    }

    @GetMapping("/calcular-promedio-negocio")
    public ResponseEntity<MensajeDTO<Integer>> calcularPromedioCalificaciones(@PathVariable String codigoNegocio) throws Exception {
        comentarioServicio.calcularPromedioCalificaciones(codigoNegocio);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.calcularPromedioCalificaciones(codigoNegocio)));
    }
}
