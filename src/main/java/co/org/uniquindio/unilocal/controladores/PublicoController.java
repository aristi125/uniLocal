package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.cliente.ItemListaLugaresCreadosDTO;
import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.dto.cuenta.LinkRecuperacionDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.entidades.Ubicacion;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ComentarioServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ModeradorServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuenta")
@RequiredArgsConstructor
public class PublicoController {

    private final ClienteServicio clienteServicio;
    private final ComentarioServicio comentarioServicio;
    private final NegocioServicio negocioServicio;
    private final ModeradorServicio moderadorServicio;

    @GetMapping("/enviar-link-recuperacion-password-cliente")
    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacionCliente(@Valid @RequestBody LinkRecuperacionDTO linkRecuperacionDTO) throws Exception {
        clienteServicio.enviarLinkRecuperacionCliente(linkRecuperacionDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha enviado un link de recuperación a su correo" ));
    }

    @GetMapping("/enviar-link-recuperacion-password-moderador")
    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacionModerador(@Valid @RequestBody LinkRecuperacionDTO linkRecuperacionDTO) throws Exception {
        moderadorServicio.enviarLinkRecuperacionModerador(linkRecuperacionDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha enviado un link de recuperación a su correo" ));
    }

    @GetMapping("/buscar-negocio-nombre/{nombre}")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioNombre(@PathVariable String nombre) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.buscarNegocioNombre(nombre)));
    }

    @GetMapping("/buscar-negocio-categoria/{categoria}")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioCategoria(@PathVariable CategoriaNegocio categoria) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.buscarNegocioCategoria(categoria)));
    }

    @GetMapping("/buscar-negocio-distancia/{distancia}")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioDistancia(@PathVariable double distancia, @PathVariable Ubicacion ubicacionCliente) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.buscarNegocioDistancia(distancia, ubicacionCliente)));
    }

    @GetMapping("/listar-comentarios-negocio/{idNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemListaComentariosDTO>>> listarComentariosNegocio(@PathVariable String idNegocio) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.listarComentariosNegocio(idNegocio)));
    }

    @GetMapping("/filtar-estado")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> filtrarPorEstado(@Valid @RequestBody EstadoNegocio estadoNegocio)throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.filtrarPorEstado(estadoNegocio)));
    }

    @GetMapping("/buscar-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<Negocio>> buscarNegocio(@PathVariable String codigoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocio(codigoNegocio)));
    }

}
