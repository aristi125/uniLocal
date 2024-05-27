package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.*;
import co.org.uniquindio.unilocal.dto.cliente.ItemListaLugaresCreadosDTO;
import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.dto.cuenta.LinkRecuperacionDTO;
import co.org.uniquindio.unilocal.dto.negocio.DetalleNegocioDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.CategoriaNegocio;
import co.org.uniquindio.unilocal.modelo.enumeracion.Ciudades;
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
@RequestMapping("/api/publico")
@RequiredArgsConstructor
public class PublicoController {

    private final ClienteServicio clienteServicio;
    private final ComentarioServicio comentarioServicio;
    private final NegocioServicio negocioServicio;
    private final ModeradorServicio moderadorServicio;

    @GetMapping("/listar-ciudades")
    public ResponseEntity<MensajeDTO<List<Ciudades>>> listarCiudades() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.listarCiudades()));
    }

    @GetMapping("/listar-categoria-negocio")
    public ResponseEntity<MensajeDTO<List<Ciudades>>> listarCategoriaNegocio() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.listarCiudades()));
    }

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

    @GetMapping("/buscar-negocio-nombre")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioNombre(@Valid @RequestBody BusquedaNombreDTO busquedaNombreDTO) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.buscarNegocioNombre(busquedaNombreDTO)));
    }

    @GetMapping("/buscar-negocio-categoria/{categoria}")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioCategoria(@PathVariable CategoriaNegocioDTO categoriaNegocioDTO) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.buscarNegocioCategoria(categoriaNegocioDTO)));
    }

    @GetMapping("/buscar-negocio-distancia")
    public ResponseEntity<MensajeDTO<List<ItemListaLugaresCreadosDTO>>> buscarNegocioDistancia(@Valid @RequestBody BusquedaDistanciaDTO busquedaDistanciaDTO) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.buscarNegocioDistancia(busquedaDistanciaDTO)));
    }

    @GetMapping("/listar-comentarios-negocio/{idNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemListaComentariosDTO>>> listarComentariosNegocio(@PathVariable String idNegocio) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.listarComentariosNegocio(idNegocio)));
    }

    @GetMapping("/filtar-estado")
    public ResponseEntity<MensajeDTO<List<DetalleNegocioDTO>>> filtrarPorEstado(@Valid @RequestBody EstadoNegocioDTO estadoNegocioDTO)throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.filtrarPorEstado(estadoNegocioDTO)));
    }

    @GetMapping("/buscar-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<Negocio>> buscarNegocio(@PathVariable String codigoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocio(codigoNegocio)));
    }

}
