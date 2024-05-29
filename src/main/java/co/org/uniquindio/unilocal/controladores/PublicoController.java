package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.*;
import co.org.uniquindio.unilocal.dto.cliente.ItemListaLugaresCreadosDTO;
import co.org.uniquindio.unilocal.dto.comentario.ItemListaComentariosDTO;
import co.org.uniquindio.unilocal.dto.cuenta.LinkRecuperacionDTO;
import co.org.uniquindio.unilocal.dto.negocio.DetalleNegocioDTO;
import co.org.uniquindio.unilocal.dto.negocio.ItemNegocioDTO;
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

    @PostMapping("/enviar-link-recuperacion-password")
    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacion(@Valid @RequestBody LinkRecuperacionDTO linkRecuperacionDTO) throws Exception {
        try {
            clienteServicio.enviarLinkRecuperacionCliente(linkRecuperacionDTO);
        } catch (Exception e) {
            try {
                moderadorServicio.enviarLinkRecuperacionModerador(linkRecuperacionDTO);
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body(new MensajeDTO<>(true, "No se pudo enviar el link de recuperación ni como cliente ni como moderador"));
            }
        }
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se ha enviado un link de recuperación a su correo"));
    }

//   @GetMapping("/enviar-link-recuperacion-password-cliente")
//    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacionCliente(@Valid @RequestBody LinkRecuperacionDTO linkRecuperacionDTO) throws Exception {
//        clienteServicio.enviarLinkRecuperacionCliente(linkRecuperacionDTO);
//        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha enviado un link de recuperación a su correo" ));
//    }
//
//    @GetMapping("/enviar-link-recuperacion-password-moderador")
//    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacionModerador(@Valid @RequestBody LinkRecuperacionDTO linkRecuperacionDTO) throws Exception {
//        moderadorServicio.enviarLinkRecuperacionModerador(linkRecuperacionDTO);
//        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha enviado un link de recuperación a su correo" ));
//    }

    @GetMapping("/buscar-negocio-nombre/{nombre}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> buscarNegocioNombre(@Valid @RequestBody String nombre) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.buscarNegocioNombre(nombre)));
    }

    @GetMapping("/listar-negocios")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegocios() throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.listarNegocios()));
    }

    @GetMapping("/buscar-negocio-categoria/{categoriaNegocioDTO}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> buscarNegocioCategoria(@PathVariable CategoriaNegocio categoriaNegocioDTO) throws Exception {
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

    @GetMapping("/filtrar-estado/{estadoNegocioDTO}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarPorEstado(@Valid @RequestBody EstadoNegocio estadoNegocioDTO)throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.filtrarPorEstado(estadoNegocioDTO)));
    }

    @GetMapping("/buscar-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<Negocio>> buscarNegocio(@PathVariable String codigoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocio(codigoNegocio)));
    }

}
