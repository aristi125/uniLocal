package co.org.uniquindio.unilocal.controladores;

import co.org.uniquindio.unilocal.dto.MensajeDTO;
import co.org.uniquindio.unilocal.dto.Moderador.RevisionesModeradorDTO;
import co.org.uniquindio.unilocal.dto.cliente.ItemDetalleClienteDTO;
import co.org.uniquindio.unilocal.dto.comentario.RevisarComentariosDTO;
import co.org.uniquindio.unilocal.dto.cuenta.CambioPasswordDTO;
import co.org.uniquindio.unilocal.dto.negocio.ItemNegociosRevisionDTO;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import co.org.uniquindio.unilocal.modelo.documentos.HistorialRevision;
import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import co.org.uniquindio.unilocal.servicios.interfaces.ClienteServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.ModeradorServicio;
import co.org.uniquindio.unilocal.servicios.interfaces.NegocioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderadores")
@RequiredArgsConstructor
public class ModeradorController {

    private final ClienteServicio clienteServicio;
    private final ModeradorServicio moderadorServicio;
    private final NegocioServicio negocioServicio;

    @GetMapping("/listar-clientes")
    public ResponseEntity<MensajeDTO<List<ItemDetalleClienteDTO>>> listarClientes() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clienteServicio.listarClientes()));
    }

    @GetMapping("/obtener-historial-revisiones/{idNegocio}")
    public ResponseEntity<MensajeDTO<List<HistorialRevision>>> obtenerHistorialRevisiones(@PathVariable String idNegocio) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, moderadorServicio.obtenerHistorialRevisiones(idNegocio)));
    }

    @GetMapping("/revisar-comentarios/{codigo}")
    public ResponseEntity<MensajeDTO<List<RevisarComentariosDTO>>> revisarComentarios(@PathVariable String codigo) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, moderadorServicio.revisarComentarios(codigo)));
    }
    
    @GetMapping("/listar-revisones/{estadoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegociosRevisionDTO>>> listarRevisiones(@PathVariable EstadoNegocio estadoNegocio) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, moderadorServicio.listarRevisiones(estadoNegocio)));
    }

    @PutMapping("/bloquear-usuario/{codigo}")
        public ResponseEntity<MensajeDTO<String>> bloquearUsuario(@PathVariable String codigo) throws Exception {
        clienteServicio.bloquearUsuario(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(true, "El Usuario ha sido bloqueado"));
    }

    @PutMapping("/rechazar-negocio")
    public ResponseEntity<MensajeDTO<String>> rechazarNegocio(@Valid @RequestBody RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        moderadorServicio.rechazarNegocio(revisionesModeradorDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(true, "El Negocio ha sido rechazado"));
    }
    @PutMapping("/aprobar-negocio")
    public ResponseEntity<MensajeDTO<String>> aprobarNegocio(@Valid @RequestBody RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        moderadorServicio.aprobarNegocio(revisionesModeradorDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(true, "El Negocio ha sido aprobado"));
    }

    @PutMapping("/cambiar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@Valid @RequestBody CambioPasswordDTO cambioPasswordDTO) throws Exception {
        moderadorServicio.cambiarPassword(cambioPasswordDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se ha cambiado su contraseña" ));
    }


    @GetMapping("/listar-negocio-estado/{estadoNegocio}")
    public ResponseEntity<MensajeDTO<List<Negocio>>> listarNegociosEstado(@PathVariable EstadoNegocio estadoNegocio) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.listarNegociosEstado(estadoNegocio)));
    }
}
